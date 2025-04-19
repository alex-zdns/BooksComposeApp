package com.alexzdns.books.domain.repository

import kotlinx.coroutines.flow.Flow

interface FavoritesBooksRepository {
    fun isFavoritesFlow(bookId: String): Flow<Boolean>
    suspend fun isFavorites(bookId: String): Boolean
    suspend fun addToFavorites(bookId: String)
    suspend fun removeToFavorites(bookId: String)
}