package com.alexzdns.books.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.alexzdns.books.R
import com.alexzdns.books.ui.navigation.destination.FAVORITES_SCREEN_ROUTE
import com.alexzdns.books.ui.navigation.destination.MAIN_SCREEN_ROUTE

sealed class BottomNavigationTab(
    val route: String,
    @StringRes val title: Int,
    @DrawableRes val icon: Int
) {
    data object Search : BottomNavigationTab(
        route = MAIN_SCREEN_ROUTE,
        title = R.string.search_title,
        icon = R.drawable.ic_search_24
    )

    data object Favorite : BottomNavigationTab(
        route = FAVORITES_SCREEN_ROUTE,
        title = R.string.favorites_title,
        icon = R.drawable.ic_favorite_24
    )
}