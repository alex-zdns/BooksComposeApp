package com.alexzdns.books.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.alexzdns.books.ui.features.history.HISTORY_SCREEN_ROUTE
import com.alexzdns.books.R
import com.alexzdns.books.ui.features.search.R as SearchR
import com.alexzdns.books.ui.features.favorites.R as FavoritesR
import com.alexzdns.books.ui.features.history.R as HistoryR
import com.alexzdns.books.ui.features.favorites.FAVORITES_SCREEN_ROUTE
import com.alexzdns.books.ui.features.search.SEARCH_SCREEN_ROUTE

sealed class BottomNavigationTab(
    val route: String,
    @StringRes val title: Int,
    @DrawableRes val icon: Int
) {
    data object Search : BottomNavigationTab(
        route = SEARCH_SCREEN_ROUTE,
        title = SearchR.string.search_title,
        icon = R.drawable.ic_search_24
    )

    data object Favorite : BottomNavigationTab(
        route = FAVORITES_SCREEN_ROUTE,
        title = FavoritesR.string.favorites_title,
        icon = R.drawable.ic_favorite_24
    )

    data object History : BottomNavigationTab(
        route = HISTORY_SCREEN_ROUTE,
        title = HistoryR.string.history_title,
        icon = HistoryR.drawable.ic_history_24
    )
}