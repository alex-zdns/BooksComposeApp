package com.alexzdns.books.ui.features.search.filtres

import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.dialog
import androidx.navigation.navArgument
import com.alexzdns.books.domain.models.BookSortType

const val SEARCH_FILTER_DIALOG_ROUTE = "searchFilterDialog"
private const val SORT_TYPE_KEY = "searchFilterDialog"

fun NavGraphBuilder.filters(onBackClick: () -> Unit) {
    dialog(
        route = "$SEARCH_FILTER_DIALOG_ROUTE/{$SORT_TYPE_KEY}",
        dialogProperties = DialogProperties(usePlatformDefaultWidth = false),
        arguments = listOf(
            navArgument(SORT_TYPE_KEY) {
                type = NavType.EnumType(BookSortType::class.java)
                nullable = false
                defaultValue = BookSortType.NONE
            }
        )
    ) {
        SearchFiltersDialog(
            onDismiss = onBackClick
        )
    }
}

fun NavController.navigateToSearchFilters(type: BookSortType?) {
    navigate("$SEARCH_FILTER_DIALOG_ROUTE/${type?.name}")
}
