package com.alexzdns.books.domain.repository

import com.alexzdns.books.domain.models.BookWithViewTime
import kotlinx.coroutines.flow.Flow

interface HistorySeenBooksRepository {
    fun getBooksHistory(): Flow<List<BookWithViewTime>>
    suspend fun trackBookDetailsSeen(bookId: String)
}