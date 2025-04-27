package com.alexzdns.books.ui.features.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexzdns.books.domain.repository.BookRepository
import com.alexzdns.books.domain.repository.FavoritesBooksRepository
import com.alexzdns.books.domain.repository.HistorySeenBooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val bookRepository: BookRepository,
    private val favoritesBooksRepository: FavoritesBooksRepository,
    private val historySeenBooksRepository: HistorySeenBooksRepository
) : ViewModel() {

    private val _bookDetailsStateFlow = MutableStateFlow<BookDetailsState>(BookDetailsState.Loading)
    val bookDetailsStateFlow = _bookDetailsStateFlow.asStateFlow()

    private val _bookFavoriteStateFlow = MutableStateFlow<Boolean>(false)
    val bookFavoriteStateFlow = _bookFavoriteStateFlow.asStateFlow()

    val bookId: String =
        savedStateHandle.get<String>(ID_KEY) ?: error("bookId must be not null")

    init {
        trackBookDetailsView()
        getBook()
        observeFavoritesState()
    }

    fun retry() {
        getBook()
    }

    private fun getBook() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val bookItem = bookRepository.getBook(bookId)
                _bookDetailsStateFlow.emit(BookDetailsState.Result(bookItem))
            } catch (_: Exception) {
                _bookDetailsStateFlow.emit(BookDetailsState.Error)
            }
        }
    }

    private fun observeFavoritesState() {
        viewModelScope.launch(Dispatchers.IO) {
            favoritesBooksRepository.isFavoritesFlow(bookId).collect {
                _bookFavoriteStateFlow.value = it
            }
        }
    }

    private fun trackBookDetailsView() {
        viewModelScope.launch(Dispatchers.IO) {
            historySeenBooksRepository.trackBookDetailsSeen(bookId)
        }
    }
}