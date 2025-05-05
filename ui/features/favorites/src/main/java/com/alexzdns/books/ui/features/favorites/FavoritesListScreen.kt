package com.alexzdns.books.ui.features.favorites

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alexzdns.books.ui.common.favorites.FavoritesOperationViewModel
import com.alexzdns.books.ui.common.list.BooksListView
import com.alexzdns.books.ui.core.views.Toolbar

@Composable
fun FavoritesScreen(
    onBackClick: () -> Unit,
    onBookClick: (String) -> Unit,
    viewModel: FavoritesListViewModel = hiltViewModel(),
) {
    val booksState = viewModel.favoriteBooksStateFlow.collectAsStateWithLifecycle(emptyList())
    val favoritesOperationViewModel: FavoritesOperationViewModel =
        hiltViewModel(LocalActivity.current as ViewModelStoreOwner)

    Column {
        Toolbar(
            onBackClick = onBackClick,
            title = R.string.favorites_title
        )
        BooksListView(
            books = booksState.value,
            onBookClick = onBookClick,
            onFavorite = favoritesOperationViewModel::onFavoriteClick
        )
    }
}
