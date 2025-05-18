package com.alexzdns.books.domain.models

data class BookItem(
    val id: String,
    val title: String,
    val authors: List<String>,
    val publishedYear: String?,
    val description: String?,
    val thumbnailUrl: String?,
    val imageUrl: String?,
)