package com.alexzdns.books.ui.features.details

import com.alexzdns.books.domain.models.BookItem

sealed interface BookDetailsState {
    data object Loading: BookDetailsState
    data object Error: BookDetailsState
    data class Result(val bookItem: BookItem): BookDetailsState
}