package com.alexzdns.books.domain.repository

import com.alexzdns.books.domain.models.BookItem
import kotlinx.coroutines.flow.Flow

interface BookCacheRepository {
    suspend fun insertAll(books: List<BookItem>)
    suspend fun getCachedBook(bookId: String): BookItem?
    fun getAllFavoritesBooks(): Flow<List<BookItem>>
}