package com.alexzdns.books.domain.repository

interface HistorySeenBooksRepository {
    suspend fun trackBookDetailsSeen(bookId: String)
}