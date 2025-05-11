package com.alexzdns.books.ui.features.search.filtres

import com.alexzdns.books.domain.models.BookSortType

data class BookSearchFilterState(
    val sortTypes: List<BookSortType> = listOf(BookSortType.NEWEST, BookSortType.RELEVANCE),
    val selectedSortType: BookSortType = BookSortType.NONE,
    val isEnabledApplyButton: Boolean = false,
)
