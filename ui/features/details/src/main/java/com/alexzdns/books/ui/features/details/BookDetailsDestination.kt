package com.alexzdns.books.ui.features.details

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val DETAILS_ROUTE = "bookDetails"
const val ID_KEY = "id"

fun NavGraphBuilder.bookDetails(onBackClick: () -> Unit) {
    composable(
        route = "$DETAILS_ROUTE/{$ID_KEY}",
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
    navigate("$DETAILS_ROUTE/$bookId")
}
