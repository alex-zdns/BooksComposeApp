package com.alexzdns.books.ui.models

import com.alexzdns.books.domain.models.BookItem

data class BookWithViewTime(
    val book: BookItem,
    val timestamp: Long,
)