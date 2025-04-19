package com.alexzdns.books.ui.navigation.destination

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.alexzdns.books.ui.details.DetailsBookScreen

private const val BASE_ROUTE = "bookDetails"
const val ID_KEY = "id"

fun NavGraphBuilder.bookDetails(onBackClick: () -> Unit) {
    composable(
        route = "$BASE_ROUTE/{$ID_KEY}",
        arguments = listOf(
            navArgument(ID_KEY) {
                type = NavType.StringType
                nullable = false
            }
        )
    ) {
        DetailsBookScreen(onBackClick = onBackClick)
    }
}

fun NavController.navigateToBookDetails(bookId: String) {
    navigate("$BASE_ROUTE/$bookId")
}
