package com.alexzdns.books.domain.models

data class BookPage(
    val books: List<BookItem>,
    val totalItems: Int,
    val currentPageSize: Int,
)