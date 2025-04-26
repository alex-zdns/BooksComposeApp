package com.alexzdns.books.domain.models

data class BookWithViewTime(
    val book: BookItem,
    val timestamp: Long,
)
