package com.alexzdns.books.ui.features.search.filtres

import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.dialog
import androidx.navigation.navArgument
import com.alexzdns.books.domain.models.BookSortType

internal const val SEARCH_FILTER_DIALOG_ROUTE = "searchFilterDialog"
internal const val SORT_TYPE_KEY = "searchFilterDialog"
internal const val RESULT_SORT_TYPE_KEY = "result_sort_type"

fun NavGraphBuilder.filters(navController: NavController) {
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
            onDismiss = navController::popBackStack,
            onApply = {
                navController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.set(RESULT_SORT_TYPE_KEY, it)
                navController.popBackStack()
            }
        )
    }
}

fun NavController.navigateToSearchFilters(type: BookSortType?) {
    navigate("$SEARCH_FILTER_DIALOG_ROUTE/${type?.name}")
}
