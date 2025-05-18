package com.alexzdns.books.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class VolumeInfoDTO(
    @SerialName("title")
    val title: String,
    @SerialName("authors")
    val authors: List<String> = emptyList(),
    @SerialName("publishedDate")
    val publishedDate: String? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("imageLinks")
    val imageLinks: ImageLinksDTO? = null
)
