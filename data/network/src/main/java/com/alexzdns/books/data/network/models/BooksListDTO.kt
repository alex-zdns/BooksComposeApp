package com.alexzdns.books.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class BooksListDTO(
    @SerialName("totalItems")
    val totalItems: Int?,
    @SerialName("items")
    val items: List<BookItemDTO>?
)
