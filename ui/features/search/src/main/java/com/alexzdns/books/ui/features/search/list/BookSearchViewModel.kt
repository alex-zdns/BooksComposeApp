package com.alexzdns.books.ui.features.search.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import com.alexzdns.books.domain.models.BookSortType
import com.alexzdns.books.domain.repository.BookCacheRepository
import com.alexzdns.books.domain.repository.BookRepository
import com.alexzdns.books.domain.repository.FavoritesBooksRepository
import com.alexzdns.books.ui.core.models.BookItemUi
import com.alexzdns.books.ui.features.search.list.paging.BookPagingSource
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
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookSearchViewModel @Inject constructor(
    private val bookRepository: BookRepository,
    private val cacheRepository: BookCacheRepository,
    favoritesBooksRepository: FavoritesBooksRepository,
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
                val pagingDataFlow = state.bookList.map { pagingData ->
                    pagingData.map { bookUiItem ->
                        val isFavorite = favoritesBookIds.contains(bookUiItem.book.id)
                        if (bookUiItem.isFavorite != isFavorite) {
                            bookUiItem.copy(isFavorite = isFavorite)
                        } else {
                            bookUiItem
                        }
                    }
                }
                state.copy(pagingDataFlow)
            } else {
                state
            }
        }

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val debounceSearch = MutableStateFlow("")

    var bookSortType: BookSortType = BookSortType.NONE
        private set
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

    fun onApplyFilterClick(bookSortType: BookSortType) {
        this.bookSortType = bookSortType
        if (debounceSearch.value.isNotBlank()) {
            searchBooks(debounceSearch.value)
        }
    }

    private fun searchBooks(query: String) {
        searchJob?.cancel()

        searchJob = viewModelScope.launch(Dispatchers.IO) {
            _booksStateFlow.emit(BookSearchState.Loading)

            try {
                _booksStateFlow.emit(
                    loadSearchBooks(
                        searchQuery = query,
                    )
                )
            } catch (_: Exception) {
                _booksStateFlow.emit(BookSearchState.Error)
            }
        }
    }

    private fun loadSearchBooks(searchQuery: String): BookSearchState.Result {
        val booksFlow = Pager(PagingConfig(10)) {
            BookPagingSource(
                repository = bookRepository,
                cacheRepository = cacheRepository,
                searchQuery = searchQuery,
                sortType = bookSortType,
            )
        }.flow.cachedIn(viewModelScope).map { list ->
            list.map { book ->
                BookItemUi(
                    isFavorite = favoritesBookIds.contains(book.id),
                    book = book,
                )
            }
        }

        return BookSearchState.Result(booksFlow)
    }

    fun retrySearch() {
        searchBooks(debounceSearch.value)
    }
}
