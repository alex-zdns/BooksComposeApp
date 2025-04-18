package com.alexzdns.books.data.network.repository

import com.alexzdns.books.data.network.BooksApi
import com.alexzdns.books.data.network.mappers.BookItemNetworkMapper
import com.alexzdns.books.domain.models.BookItem
import com.alexzdns.books.domain.repository.BookRepository
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val booksApi: BooksApi,
    private val itemMapper: BookItemNetworkMapper
) : BookRepository {
    override suspend fun getBook(id: String): BookItem {
        return booksApi.getBookByVolumeId(id).let {
            itemMapper.convert(it) ?: error("network error")
        }
    }
}