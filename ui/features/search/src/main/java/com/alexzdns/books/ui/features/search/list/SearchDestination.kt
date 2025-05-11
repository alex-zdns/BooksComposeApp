package com.alexzdns.books.ui.features.search.list

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.alexzdns.books.domain.models.BookSortType

const val SEARCH_SCREEN_ROUTE = "search"

fun NavGraphBuilder.search(
    onBookClick: (String) -> Unit,
    onFilterClick: (type: BookSortType?) -> Unit
) {
    composable(SEARCH_SCREEN_ROUTE) {
        BookSearchScreen(
            onBookClick = onBookClick,
            onFilterClick = onFilterClick
        )
    }
}
