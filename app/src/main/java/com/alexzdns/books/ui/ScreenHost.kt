package com.alexzdns.books.ui

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.alexzdns.books.ui.navigation.BookAppNavigation
import com.alexzdns.books.ui.navigation.BottomNavigationTab
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavDestination.Companion.hierarchy
import com.alexzdns.books.R
import com.alexzdns.books.ui.favorite.common.FavoritesOperationViewModel
import com.alexzdns.books.ui.models.NotificationEvent
import com.alexzdns.books.ui.navigation.destination.DETAILS_ROUTE
import com.alexzdns.books.ui.theme.Typography
import com.alexzdns.books.ui.theme.blue
import com.alexzdns.books.ui.theme.lightGrey

val LocalSnackbarHostState = compositionLocalOf<SnackbarHostState> {
    error("No Snackbar Host State")
}

@Composable
fun ScreenHost() {
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }

    CompositionLocalProvider(
        values = arrayOf(
            LocalSnackbarHostState provides snackbarHostState
        )
    ) {
        SnackBarObserver()

        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState) { snackbarData: SnackbarData ->
                    CustomSnackBar(
                        message = snackbarData.visuals.message,
                        containerColor = blue,
                        onDismissClick = { snackbarData.dismiss() }
                    )
                }
            },
            contentWindowInsets = WindowInsets.ime,
            modifier = Modifier.fillMaxSize(),
            bottomBar = { BottomNavigationBar(navController) },
        ) { padding ->
            Surface(modifier = Modifier.padding(padding)) {
                BookAppNavigation(navController)
            }
        }
    }
}

@Composable
private fun BottomNavigationBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val isDetailsScreen = navBackStackEntry?.destination?.route?.startsWith(DETAILS_ROUTE) == true
    if (isDetailsScreen) return

    Column {
        HorizontalDivider(
            thickness = 1.dp,
            color = lightGrey
        )
        NavigationBar(
            containerColor = Color.White
        ) {
            bottomNavigationTab.forEach { item ->
                AddItem(
                    screen = item,
                    navController,
                )
            }
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

@Composable
private fun SnackBarObserver() {
    val snackbarHostState = LocalSnackbarHostState.current
    val viewModel: FavoritesOperationViewModel =
        hiltViewModel(LocalActivity.current as ViewModelStoreOwner)

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.notificationSharedFlow.collect {
            snackbarHostState.showSnackbar(
                message = when (it) {
                    NotificationEvent.AddToFavorites -> context.getString(R.string.add_book_to_favorite_message)
                    NotificationEvent.RemoveFromFavorites -> context.getString(R.string.remove_book_from_favorite_message)
                },
                duration = SnackbarDuration.Short
            )
        }
    }
}

@Composable
fun CustomSnackBar(
    message: String,
    containerColor: Color = blue,
    onDismissClick: () -> Unit,
) {
    Snackbar(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 20.dp),
        containerColor = containerColor
    ) {
        Row(Modifier.padding(top = 22.dp, bottom = 22.dp, start = 10.dp, end = 10.dp)) {
            Text(
                text = message,
                color = Color.White,
                style = Typography.bodySmall,
                modifier = Modifier.weight(1f),
            )
            Icon(
                painter = painterResource(R.drawable.ic_close),
                contentDescription = null,
                modifier = Modifier
                    .clickable {
                        onDismissClick()
                    }
                    .padding(start = 8.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}