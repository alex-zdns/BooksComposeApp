package com.alexzdns.books.ui.features.favorites

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val FAVORITES_SCREEN_ROUTE = "favorites"

fun NavGraphBuilder.favorites(
    onBackClick: () -> Unit,
    onBookClick: (String) -> Unit
) {
    composable(FAVORITES_SCREEN_ROUTE) {
        FavoritesScreen(
            onBackClick = onBackClick,
            onBookClick = onBookClick,
        )
    }
}
