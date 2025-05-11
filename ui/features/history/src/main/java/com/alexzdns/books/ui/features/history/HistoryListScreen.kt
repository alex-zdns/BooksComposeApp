package com.alexzdns.books.ui.features.history

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alexzdns.books.ui.core.views.Toolbar
import androidx.compose.foundation.lazy.items

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HistoryListScreen(
    onBackClick: () -> Unit,
    onBookClick: (String) -> Unit,
    viewModel: HistoryListViewModel = hiltViewModel(),
) {
    val booksState = viewModel.flow.collectAsStateWithLifecycle(emptyList())

    Column {
        Toolbar(
            onBackClick = onBackClick,
            title = R.string.history_title
        )

        LazyColumn {
            booksState.value.forEach { group ->
                stickyHeader { DayStickyItem(group.day) }
                items(group.books) { book ->
                    HistoryItem(book, onBookClick)
                }
            }
        }
    }
}