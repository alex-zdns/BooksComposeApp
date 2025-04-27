package com.alexzdns.books.data.database.repository

import com.alexzdns.books.data.database.dao.HistoryBooksDao
import com.alexzdns.books.data.database.mappers.HistoryDbMapper
import com.alexzdns.books.domain.repository.HistorySeenBooksRepository
import com.alexzdns.books.domain.models.BookWithViewTime
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class HistorySeenBooksRepositoryImpl @Inject constructor(
    private val dao: HistoryBooksDao,
    private val historyMapper: HistoryDbMapper
) : HistorySeenBooksRepository {

    override fun getBooksHistory(): Flow<List<BookWithViewTime>> {
        return dao.getBooksWithViewTime().map { it.map(historyMapper::fromEntity) }
    }

    override suspend fun trackBookDetailsSeen(bookId: String) {
        dao.insert(historyMapper.toEntity(bookId))
    }
}