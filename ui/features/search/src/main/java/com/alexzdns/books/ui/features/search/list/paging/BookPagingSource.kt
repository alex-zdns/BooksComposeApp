package com.alexzdns.books.ui.features.search.list.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.alexzdns.books.domain.models.BookItem
import com.alexzdns.books.domain.models.BookSortType
import com.alexzdns.books.domain.repository.BookCacheRepository
import com.alexzdns.books.domain.repository.BookRepository

class BookPagingSource(
    private val repository: BookRepository,
    private val cacheRepository: BookCacheRepository,
    private val searchQuery: String,
    private val sortType: BookSortType,
) : PagingSource<Int, BookItem>() {

    companion object {
        private const val INITIAL_START_INDEX = 0
        private const val DEFAULT_PAGE_SIZE = 10
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BookItem> {
        return try {
            loadPage(params)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private suspend fun loadPage(params: LoadParams<Int>): LoadResult.Page<Int, BookItem> {
        val startIndex = params.key ?: INITIAL_START_INDEX
        val page = repository.searchBooks(
            query = searchQuery,
            sortType = sortType,
            startIndex = startIndex
        )

        // fixme Вынести в UseCase
        cacheRepository.insertAll(page.books)

        val previousKey = if (startIndex == INITIAL_START_INDEX) null else startIndex - DEFAULT_PAGE_SIZE
        val nextKey = if (startIndex + page.currentPageSize >= page.totalItems) null else startIndex + page.currentPageSize

        return LoadResult.Page(
            data = page.books,
            prevKey = previousKey,
            nextKey = nextKey,
        )
    }

    override fun getRefreshKey(state: PagingState<Int, BookItem>): Int? {
        return state.anchorPosition
    }
}