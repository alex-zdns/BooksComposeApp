package com.alexzdns.books.data.network.extensions

import com.alexzdns.books.domain.models.BookSortType
import com.alexzdns.books.domain.models.BookSortType.*

fun BookSortType.getQuery(): String? = when(this) {
    NEWEST -> "newest"
    RELEVANCE -> "relevance"
    NONE -> null
}