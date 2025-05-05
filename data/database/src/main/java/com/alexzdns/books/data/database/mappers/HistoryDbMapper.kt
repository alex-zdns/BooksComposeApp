package com.alexzdns.books.data.database.mappers

import com.alexzdns.books.data.database.models.HistoryEntity
import com.alexzdns.books.data.database.models.BookEntityWithViewTime
import com.alexzdns.books.domain.models.BookWithViewTime
import java.time.Instant
import java.time.LocalDateTime
import java.util.TimeZone
import javax.inject.Inject

internal class HistoryDbMapper @Inject constructor(
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
            timestamp =
                LocalDateTime.ofInstant(
                    Instant.ofEpochMilli(entity.timestamp), TimeZone
                        .getDefault().toZoneId()
                )
        )
    }
}