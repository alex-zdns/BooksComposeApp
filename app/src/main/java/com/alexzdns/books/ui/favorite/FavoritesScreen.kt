package com.alexzdns.books.ui.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.alexzdns.books.ui.theme.Typography

@Composable
fun FavoritesScreen(
    onBookClick: (String) -> Unit = {},
    viewModel: FavoritesViewModel = hiltViewModel(),
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "FavoritesScreen",
            style = Typography.headlineLarge,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}