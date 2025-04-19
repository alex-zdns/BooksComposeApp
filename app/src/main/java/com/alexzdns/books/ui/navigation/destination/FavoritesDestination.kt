package com.alexzdns.books.ui.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.alexzdns.books.ui.favorite.list.FavoritesScreen

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
