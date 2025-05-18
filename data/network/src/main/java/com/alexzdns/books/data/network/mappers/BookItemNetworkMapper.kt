package com.alexzdns.books.data.network.mappers

import com.alexzdns.books.data.network.models.BookItemDTO
import com.alexzdns.books.domain.models.BookItem
import javax.inject.Inject

internal class BookItemNetworkMapper @Inject constructor() {
    fun convert(model: BookItemDTO): BookItem? {
        if (model.volumeInfo == null)
            return null

        return BookItem(
            id = model.id,
            title = model.volumeInfo.title,
            authors = model.volumeInfo.authors,
            description = model.volumeInfo.description,
            publishedYear = model.volumeInfo.publishedDate.orEmpty(),
            thumbnailUrl = convertImage(model.volumeInfo.imageLinks?.thumbnail),
            imageUrl = convertImage(model.volumeInfo.imageLinks?.large)
        )
    }

    private fun convertImage(imageUrl: String?): String? {
        imageUrl ?: return null
        return imageUrl.replace("http://", "https://")
    }
}