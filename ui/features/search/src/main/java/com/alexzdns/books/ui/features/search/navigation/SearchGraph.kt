package com.alexzdns.books.ui.features.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.alexzdns.books.ui.features.search.filtres.filters
import com.alexzdns.books.ui.features.search.filtres.navigateToSearchFilters
import com.alexzdns.books.ui.features.search.list.SEARCH_SCREEN_ROUTE
import com.alexzdns.books.ui.features.search.list.search

const val SEARCH_GRAPH_ROUTE = "search_graph"

fun NavGraphBuilder.searchGraph(navController: NavController, onBookClick: (String) -> Unit) {
    navigation(
        startDestination = SEARCH_SCREEN_ROUTE,
        route = SEARCH_GRAPH_ROUTE
    ) {
        search(onBookClick, navController::navigateToSearchFilters)
        filters(navController)
    }
}
