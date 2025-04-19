package com.alexzdns.books.data.database.mappers

import com.alexzdns.books.data.database.models.HistoryEntity
import javax.inject.Inject

class HistoryDbMapper @Inject constructor() {
    fun toEntity(bookId: String): HistoryEntity {
        return HistoryEntity(
            id = 0,
            bookId = bookId,
            timestamp = System.currentTimeMillis()
        )
    }
}