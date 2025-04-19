package com.alexzdns.books.ui.models

import com.alexzdns.books.domain.models.BookItem

data class BookItemUi(
    val book: BookItem,
    val isFavorite: Boolean,
)