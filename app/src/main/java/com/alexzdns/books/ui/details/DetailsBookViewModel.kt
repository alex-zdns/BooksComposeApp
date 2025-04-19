package com.alexzdns.books.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexzdns.books.domain.repository.BookRepository
import com.alexzdns.books.ui.navigation.destination.ID_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val bookRepository: BookRepository,
) : ViewModel() {

    private val _bookDetailsStateFlow = MutableStateFlow<BookDetailsState>(BookDetailsState.Loading)
    val bookDetailsStateFlow = _bookDetailsStateFlow.asStateFlow()

    private val _bookFavoriteStateFlow = MutableStateFlow<Boolean>(false)
    val bookFavoriteStateFlow = _bookFavoriteStateFlow.asStateFlow()

    private val bookId: String =
        savedStateHandle.get<String>(ID_KEY) ?: error("bookId must be not null")

    init {
        getBook()
    }

    private fun getBook() {
        viewModelScope.launch {
            try {
                val bookItem = bookRepository.getBook(bookId)
                _bookDetailsStateFlow.emit(BookDetailsState.Result(bookItem))
            } catch (e: Exception) {
                _bookDetailsStateFlow.emit(BookDetailsState.Error)
            }
        }
    }

    fun retry() {
        getBook()
    }

    fun onFavoriteClick() {
        _bookFavoriteStateFlow.tryEmit(!_bookFavoriteStateFlow.value)
    }
}