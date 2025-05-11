package com.alexzdns.books.ui.features.search.list

import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.alexzdns.books.domain.models.BookSortType
import com.alexzdns.books.ui.features.search.filtres.RESULT_SORT_TYPE_KEY

const val SEARCH_SCREEN_ROUTE = "search"

fun NavGraphBuilder.search(
    onBookClick: (String) -> Unit,
    onFilterClick: (type: BookSortType?) -> Unit
) {
    composable(SEARCH_SCREEN_ROUTE) { entry ->
        val viewModel: BookSearchViewModel = hiltViewModel()

        LaunchedEffect(Unit) {
            entry.savedStateHandle.getStateFlow<BookSortType?>(RESULT_SORT_TYPE_KEY, null).collect {
                it ?: return@collect
                viewModel.onApplyFilterClick(it)
            }
        }

        BookSearchScreen(
            onBookClick = onBookClick,
            onFilterClick = onFilterClick,
            viewModel = viewModel,
        )
    }
}
