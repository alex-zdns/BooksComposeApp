package com.alexzdns.books.data.database.mappers

import com.alexzdns.books.data.database.models.FavoriteEntity
import javax.inject.Inject

class FavoritesBooksDbMapper @Inject constructor() {
    fun toEntity(bookId: String): FavoriteEntity {
        return FavoriteEntity(
            id = 0,
            bookId = bookId,
            timestamp = System.currentTimeMillis()
        )
    }
}