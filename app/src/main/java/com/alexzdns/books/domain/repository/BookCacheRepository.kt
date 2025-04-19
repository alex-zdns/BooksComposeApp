package com.alexzdns.books.domain.repository

import com.alexzdns.books.domain.models.BookItem

interface BookCacheRepository {
    suspend fun insertAll(books: List<BookItem>)
}