package com.alexzdns.books.ui.features.search

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val SEARCH_SCREEN_ROUTE = "search"

fun NavGraphBuilder.search(onBookClick: (String) -> Unit) {
    composable(SEARCH_SCREEN_ROUTE) {
        BookSearchScreen(
            onBookClick = onBookClick
        )
    }
}
