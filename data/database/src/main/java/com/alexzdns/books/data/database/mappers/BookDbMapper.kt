package com.alexzdns.books.data.database.mappers

import com.alexzdns.books.data.database.models.BookEntity
import com.alexzdns.books.domain.models.BookItem
import javax.inject.Inject

internal class BookDbMapper @Inject constructor() {
    private companion object {
        const val SEPARATOR = "$$$"
    }

    fun toEntity(bookItem: BookItem): BookEntity {
        return BookEntity(
            id = bookItem.id,
            title = bookItem.title,
            authors = bookItem.authors.joinToString(SEPARATOR),
            publishedYear = bookItem.publishedYear,
            description = bookItem.description,
            thumbnailUrl = bookItem.thumbnailUrl,
            imageUrl = bookItem.imageUrl,
        )
    }

    fun fromEntity(entity: BookEntity): BookItem {
        return BookItem(
            id = entity.id,
            title = entity.title,
            authors = entity.authors.split(SEPARATOR),
            publishedYear = entity.publishedYear,
            description = entity.description,
            thumbnailUrl = entity.thumbnailUrl,
            imageUrl = entity.imageUrl
        )
    }
}