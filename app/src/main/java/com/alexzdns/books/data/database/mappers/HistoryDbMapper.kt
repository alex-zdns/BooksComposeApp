package com.alexzdns.books.data.database.mappers

import com.alexzdns.books.data.database.models.HistoryEntity
import com.alexzdns.books.data.database.models.BookEntityWithViewTime
import com.alexzdns.books.ui.models.BookWithViewTime
import javax.inject.Inject

class HistoryDbMapper @Inject constructor(
    private val bookMapper: BookDbMapper,
) {
    fun toEntity(bookId: String): HistoryEntity {
        return HistoryEntity(
            id = 0,
            bookId = bookId,
            timestamp = System.currentTimeMillis()
        )
    }

    fun fromEntity(entity: BookEntityWithViewTime): BookWithViewTime {
        return BookWithViewTime(
            book = bookMapper.fromEntity(entity.book),
            timestamp = entity.timestamp
        )
    }
}