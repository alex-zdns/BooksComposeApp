package com.alexzdns.books.ui

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.alexzdns.books.ui.navigation.BookAppNavigation
import com.alexzdns.books.ui.navigation.BottomNavigationTab
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavDestination.Companion.hierarchy
import com.alexzdns.books.ui.navigation.destination.DETAILS_ROUTE
import com.alexzdns.books.ui.theme.Typography
import com.alexzdns.books.ui.theme.blue
import com.alexzdns.books.ui.theme.lightGrey

@Composable
fun ScreenHost() {
    val navController = rememberNavController()

    Scaffold(
        contentWindowInsets = WindowInsets.ime,
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomNavigationBar(navController) },
    ) { padding ->
        Surface(modifier = Modifier.padding(padding)) {
            BookAppNavigation(navController)
        }
    }
}

@Composable
private fun BottomNavigationBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val isDetailsScreen = navBackStackEntry?.destination?.route?.startsWith(DETAILS_ROUTE) == true
    if (isDetailsScreen) return

    NavigationBar {
        bottomNavigationTab.forEach { item ->
            AddItem(
                screen = item,
                navController,
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomNavigationTab,
    navController: NavHostController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true

    NavigationBarItem(
        label = {
            Text(
                text = stringResource(screen.title),
                style = Typography.labelMedium,
            )
        },
        icon = {
            val imageVector = ImageVector.vectorResource(screen.icon)
            Icon(imageVector, screen.route)
        },
        selected = isSelected,
        alwaysShowLabel = true,
        onClick = {
            if (!isSelected) {
                navController.navigate(screen.route)
            }
        },
        colors = NavigationBarItemColors(
            selectedTextColor = blue,
            selectedIconColor = blue,
            selectedIndicatorColor = Color.Transparent,
            unselectedIconColor = lightGrey,
            unselectedTextColor = lightGrey,
            disabledIconColor = Color.Black,
            disabledTextColor = Color.Black,
        )
    )
}

private val bottomNavigationTab = listOf(
    BottomNavigationTab.Search,
    BottomNavigationTab.Favorite,
)