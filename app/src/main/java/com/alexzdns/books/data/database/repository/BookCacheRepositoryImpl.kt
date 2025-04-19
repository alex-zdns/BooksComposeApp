package com.alexzdns.books.data.database.repository

import com.alexzdns.books.data.database.dao.BooksDao
import com.alexzdns.books.data.database.mappers.BookDbMapper
import com.alexzdns.books.domain.models.BookItem
import com.alexzdns.books.domain.repository.BookCacheRepository
import javax.inject.Inject

class BookCacheRepositoryImpl @Inject constructor(
    private val dao: BooksDao,
    private val mapper: BookDbMapper,
): BookCacheRepository {
    override suspend fun insertAll(books: List<BookItem>) {
        dao.insertAll(books.map(mapper::toEntity))
    }
}
