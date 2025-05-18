package com.alexzdns.books.data.network.repository

import com.alexzdns.books.data.network.BooksApi
import com.alexzdns.books.data.network.extensions.getQuery
import com.alexzdns.books.data.network.mappers.BookItemNetworkMapper
import com.alexzdns.books.domain.models.BookItem
import com.alexzdns.books.domain.models.BookPage
import com.alexzdns.books.domain.models.BookSortType
import com.alexzdns.books.domain.repository.BookRepository
import javax.inject.Inject

internal class BookRepositoryImpl @Inject constructor(
    private val booksApi: BooksApi,
    private val itemMapper: BookItemNetworkMapper
) : BookRepository {
    override suspend fun searchBooks(
        query: String,
        sortType: BookSortType,
        startIndex: Int
    ): BookPage {
        val page =  booksApi.searchBooksList(
            searchQuery = query,
            orderBy = sortType.getQuery(),
            startIndex = startIndex,
        )



        val items = page.items
            .orEmpty()
            .mapNotNull(itemMapper::convert)

        return BookPage(
            totalItems = page.totalItems ?: 0,
            books = items,
            currentPageSize = page.items.orEmpty().size,
        )
    }

    override suspend fun getBook(id: String): BookItem {
        return booksApi.getBookByVolumeId(id).let {
            itemMapper.convert(it) ?: error("network error")
        }
    }
}