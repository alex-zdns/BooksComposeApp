package com.alexzdns.books.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.alexzdns.books.ui.features.history.history
import com.alexzdns.books.ui.features.details.bookDetails
import com.alexzdns.books.ui.features.favorites.favorites
import com.alexzdns.books.ui.features.details.navigateToBookDetails
import com.alexzdns.books.ui.features.search.navigation.SEARCH_GRAPH_ROUTE
import com.alexzdns.books.ui.features.search.navigation.searchGraph

@Composable
fun BookAppNavigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = SEARCH_GRAPH_ROUTE,
    ) {
        searchGraph(
            navController = navController,
            onBookClick = navController::navigateToBookDetails
        )

        favorites(
            onBackClick = navController::popBackStack,
            onBookClick = navController::navigateToBookDetails
        )
        history(
            onBackClick = navController::popBackStack,
            onBookClick = navController::navigateToBookDetails
        )
        bookDetails(onBackClick = navController::popBackStack)
    }
}
