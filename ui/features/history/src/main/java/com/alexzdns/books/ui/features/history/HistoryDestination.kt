package com.alexzdns.books.ui.features.history

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val HISTORY_SCREEN_ROUTE = "history"

fun NavGraphBuilder.history(
    onBackClick: () -> Unit,
    onBookClick: (String) -> Unit
) {
    composable(HISTORY_SCREEN_ROUTE) {
        HistoryListScreen(
            onBackClick = onBackClick,
            onBookClick = onBookClick,
        )
    }
}
