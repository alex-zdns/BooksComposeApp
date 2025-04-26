package com.alexzdns.books.domain.repository

import com.alexzdns.books.domain.models.BookItem

interface BookRepository {
    suspend fun searchBooks(query: String): List<BookItem>
    suspend fun getBook(id: String): BookItem
}