package com.alexzdns.books.domain.repository

import com.alexzdns.books.domain.models.BookItem
import com.alexzdns.books.domain.models.BookPage
import com.alexzdns.books.domain.models.BookSortType

interface BookRepository {
    suspend fun searchBooks(
        query: String,
        sortType: BookSortType = BookSortType.NONE,
        startIndex: Int = 0
    ): BookPage

    suspend fun getBook(id: String): BookItem
}