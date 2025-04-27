package com.alexzdns.books.ui.details

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alexzdns.books.ui.core.R
import com.alexzdns.books.ui.core.views.ErrorView
import com.alexzdns.books.ui.core.views.LoaderView
import com.alexzdns.books.ui.common.favorites.FavoritesOperationViewModel
import com.alexzdns.books.ui.core.theme.lightGrey
import com.alexzdns.books.ui.core.theme.red

@Composable
fun DetailsBookScreen(
    onBackClick: () -> Unit,
    viewModel: BookDetailsViewModel = hiltViewModel(),
) {
    val favoritesOperationViewModel: FavoritesOperationViewModel =
        hiltViewModel(LocalActivity.current as ViewModelStoreOwner)

    val bookState = viewModel.bookDetailsStateFlow.collectAsStateWithLifecycle()
    val favoriteState = viewModel.bookFavoriteStateFlow.collectAsStateWithLifecycle()

    val onFavoriteClick =  {
        favoritesOperationViewModel.onFavoriteClick(viewModel.bookId)
    }

    Column {
        Toolbar(onBackClick, onFavoriteClick, favoriteState.value)
        when (val result = bookState.value) {
            BookDetailsState.Error -> ErrorView(viewModel::retry)
            BookDetailsState.Loading -> LoaderView()
            is BookDetailsState.Result -> DetailsBookView(result.bookItem)
        }
    }
}

@Composable
private fun Toolbar(
    onBackClick: () -> Unit,
    onFavorite: () -> Unit,
    isFavorite: Boolean,
) {
    Box(Modifier.fillMaxWidth()) {
        Image(
            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
            contentDescription = null,
            modifier = Modifier
                .clickable(onClick = onBackClick)
                .padding(vertical = 12.dp, horizontal = 20.dp)

        )

        Icon(
            painter = painterResource(id = R.drawable.ic_favorite),
            tint = if (isFavorite) red else lightGrey,
            contentDescription = null,
            modifier = Modifier
                .padding(vertical = 12.dp, horizontal = 20.dp)
                .shadow(shape = ShapeDefaults.ExtraLarge, elevation = 10.dp)
                .background(color = Color.White, shape = ShapeDefaults.ExtraLarge)
                .padding(4.dp)
                .clickable(onClick = onFavorite)
                .align(Alignment.CenterEnd)
        )
    }
}