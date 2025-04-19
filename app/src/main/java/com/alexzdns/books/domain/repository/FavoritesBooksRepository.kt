package com.alexzdns.books.domain.repository

import com.alexzdns.books.domain.models.BookItem
import kotlinx.coroutines.flow.Flow

interface FavoritesBooksRepository {
    fun isFavorites(bookId: String): Flow<Boolean>
    suspend fun addToFavorites(bookId: String)
    suspend fun removeToFavorites(bookId: String)
}