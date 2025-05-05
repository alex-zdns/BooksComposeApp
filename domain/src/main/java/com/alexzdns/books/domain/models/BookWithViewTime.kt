package com.alexzdns.books.domain.models

import java.time.LocalDateTime

data class BookWithViewTime(
    val book: BookItem,
    val timestamp: LocalDateTime,
)
