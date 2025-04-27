package com.alexzdns.books.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexzdns.books.domain.repository.BookCacheRepository
import com.alexzdns.books.domain.repository.BookRepository
import com.alexzdns.books.domain.repository.FavoritesBooksRepository
import com.alexzdns.books.ui.core.models.BookItemUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookSearchViewModel @Inject constructor(
    private val bookRepository: BookRepository,
    private val cacheRepository: BookCacheRepository,
    private val favoritesBooksRepository: FavoritesBooksRepository,
) : ViewModel() {

    companion object {
        private const val SEARCH_DEBOUNCE = 2_000L
    }

    private var favoritesBookIds = emptySet<String>()

    private val _booksStateFlow = MutableStateFlow<BookSearchState>(BookSearchState.EmptyQuery)
    val booksStateFlow = _booksStateFlow
        .combine(favoritesBooksRepository.getFavoritesBookIds()) { state, favoritesBookIds ->
            this@BookSearchViewModel.favoritesBookIds = favoritesBookIds
            if (state is BookSearchState.Result) {
                val books = state.bookList.map {
                    val isFavorite = favoritesBookIds.contains(it.book.id)
                    if (it.isFavorite != isFavorite) {
                        it.copy(isFavorite = isFavorite)
                    } else {
                        it
                    }
                }
                state.copy(books)
            } else {
                state
            }
        }

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val debounceSearch = MutableStateFlow("")

    private var searchJob: Job? = null

    init {
        initSearchFlow()
    }

    @OptIn(FlowPreview::class)
    private fun initSearchFlow() {
        debounceSearch.filter { it.isNotEmpty() }
            .debounce(SEARCH_DEBOUNCE)
            .onEach(::searchBooks)
            .launchIn(viewModelScope)
    }

    fun onQueryChange(query: String) {
        viewModelScope.launch {
            if (query.isBlank())
                _booksStateFlow.emit(BookSearchState.EmptyQuery)
            _searchQuery.emit(query)
            debounceSearch.emit(query)
        }
    }

    private fun searchBooks(query: String) {
        searchJob?.cancel()

        searchJob = viewModelScope.launch(Dispatchers.IO) {
            _booksStateFlow.emit(BookSearchState.Loading)

            try {
                val bookList = bookRepository.searchBooks(query)
                if (bookList.isNotEmpty()) {
                    cacheRepository.insertAll(bookList)
                    _booksStateFlow.emit(BookSearchState.Result(bookList.map {
                        BookItemUi(
                            isFavorite = favoritesBookIds.contains(it.id),
                            book = it,
                        )
                    }))
                } else {
                    _booksStateFlow.emit(BookSearchState.EmptyResult)
                }
            } catch (_: Exception) {
                _booksStateFlow.emit(BookSearchState.Error)
            }
        }
    }

    fun retrySearch() {
        searchBooks(debounceSearch.value)
    }
}
