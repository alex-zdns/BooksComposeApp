package com.alexzdns.books.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.alexzdns.books.ui.features.search.SEARCH_SCREEN_ROUTE
import com.alexzdns.books.ui.features.details.bookDetails
import com.alexzdns.books.ui.features.favorites.favorites
import com.alexzdns.books.ui.features.search.search
import com.alexzdns.books.ui.features.details.navigateToBookDetails

@Composable
fun BookAppNavigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = SEARCH_SCREEN_ROUTE,
    ) {
        search(onBookClick = navController::navigateToBookDetails)
        favorites(
            onBackClick = navController::popBackStack,
            onBookClick = navController::navigateToBookDetails
        )
        bookDetails(onBackClick = navController::popBackStack)
    }
}
