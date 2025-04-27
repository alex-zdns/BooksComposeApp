package com.alexzdns.books.ui.favorite.list

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alexzdns.books.R
import com.alexzdns.books.ui.common.favorites.FavoritesOperationViewModel
import com.alexzdns.books.ui.common.list.BooksListView
import com.alexzdns.books.ui.core.theme.Typography

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
        Toolbar(onBackClick)
        BooksListView(
            books = booksState.value,
            onBookClick = onBookClick,
            onFavorite = favoritesOperationViewModel::onFavoriteClick
        )
    }
}

@Composable
private fun Toolbar(
    onBackClick: () -> Unit,
) {
    Box(Modifier.fillMaxWidth()) {
        Image(
            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
            contentDescription = null,
            modifier = Modifier
                .clickable(onClick = onBackClick)
                .padding(vertical = 12.dp, horizontal = 20.dp)

        )

        Text(
            text = stringResource(R.string.favorites_title),
            color = Color.Black,
            style = Typography.headlineLarge,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}