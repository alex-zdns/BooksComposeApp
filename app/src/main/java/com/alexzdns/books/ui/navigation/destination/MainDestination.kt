package com.alexzdns.books.ui.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.alexzdns.books.ui.search.BookSearchScreen

const val MAIN_SCREEN_ROUTE = "main"

fun NavGraphBuilder.main(onBookClick: (String) -> Unit) {
    composable(MAIN_SCREEN_ROUTE) {
        BookSearchScreen(
            onBookClick = onBookClick
        )
    }
}
