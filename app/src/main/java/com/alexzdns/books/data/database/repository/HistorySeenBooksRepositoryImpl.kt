package com.alexzdns.books.data.database.repository

import com.alexzdns.books.data.database.dao.HistoryBooksDao
import com.alexzdns.books.data.database.mappers.HistoryDbMapper
import com.alexzdns.books.domain.repository.HistorySeenBooksRepository
import javax.inject.Inject

class HistorySeenBooksRepositoryImpl @Inject constructor(
    private val dao: HistoryBooksDao,
    private val historyMapper: HistoryDbMapper
) :HistorySeenBooksRepository {
    override suspend fun trackBookDetailsSeen(bookId: String) {
        dao.insert(historyMapper.toEntity(bookId))
    }
}