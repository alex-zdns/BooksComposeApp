package com.alexzdns.books.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.alexzdns.books.ui.navigation.destination.MAIN_SCREEN_ROUTE
import com.alexzdns.books.ui.navigation.destination.bookDetails
import com.alexzdns.books.ui.navigation.destination.main
import com.alexzdns.books.ui.navigation.destination.navigateToBookDetails

@Composable
fun BookAppNavigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = MAIN_SCREEN_ROUTE,
    ) {
        main(
            onBookClick = navController::navigateToBookDetails
        )

        bookDetails(navController::popBackStack)
    }
}
