package com.alexzdns.books.data.database.repository

import com.alexzdns.books.data.database.dao.BooksDao
import com.alexzdns.books.data.database.mappers.BookDbMapper
import com.alexzdns.books.domain.models.BookItem
import com.alexzdns.books.domain.repository.BookCacheRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class BookCacheRepositoryImpl @Inject constructor(
    private val dao: BooksDao,
    private val mapper: BookDbMapper,
) : BookCacheRepository {
    override suspend fun insertAll(books: List<BookItem>) {
        dao.insertAll(books.map(mapper::toEntity))
    }

    override fun getAllFavoritesBooks(): Flow<List<BookItem>> {
        return dao.getFavoritesBook().map { list ->
            list.map(mapper::fromEntity)
        }
    }
}
