package com.alexzdns.books.ui.features.search.extensions

import androidx.annotation.StringRes
import com.alexzdns.books.domain.models.BookSortType
import com.alexzdns.books.ui.features.search.R

@StringRes
fun BookSortType.getNameRes(): Int = when(this) {
    BookSortType.NEWEST -> R.string.sort_type_newest
    BookSortType.RELEVANCE -> R.string.sort_type_relevance
    BookSortType.NONE -> R.string.sort_type_none
}