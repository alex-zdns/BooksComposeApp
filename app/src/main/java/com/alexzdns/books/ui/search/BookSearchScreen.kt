package com.alexzdns.books.ui.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alexzdns.books.R
import com.alexzdns.books.ui.common.ErrorView
import com.alexzdns.books.ui.common.LoaderView
import com.alexzdns.books.ui.common.MessageView
import com.alexzdns.books.ui.list.BooksListView
import com.alexzdns.books.ui.theme.lightGrey

@Composable
fun BookSearchScreen(
    onBookClick: (String) -> Unit = {},
    viewModel: BookSearchViewModel = hiltViewModel(),
) {
    val queryState = viewModel.searchQuery.collectAsStateWithLifecycle()
    val screenState = viewModel.booksStateFlow.collectAsStateWithLifecycle()

    Column {
        Toolbar(queryState.value, viewModel::onQueryChange)

        when (val result = screenState.value) {
            BookSearchState.EmptyQuery -> MessageView(R.string.empty_query_message)
            BookSearchState.EmptyResult -> MessageView(R.string.empty_result_message)
            BookSearchState.Error -> ErrorView(viewModel::retrySearch)
            BookSearchState.Loading -> LoaderView()
            is BookSearchState.Result -> BooksListView(result.bookList, onBookClick)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Toolbar(
    query: String,
    onQueryChange: (String) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 20.dp, end = 20.dp, bottom = 12.dp),
        inputField = {
            SearchBarDefaults.InputField(
                query = query,
                onQueryChange = onQueryChange,
                onSearch = {
                    keyboardController?.hide()
                },
                expanded = false,
                onExpandedChange = { },
                leadingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = null,
                        tint = lightGrey
                    )
                },
                trailingIcon = {
                    if (query.isNotBlank()) {
                        Icon(
                            Icons.Default.Clear,
                            contentDescription = "clear",
                            modifier = Modifier.clickable {
                                onQueryChange("")
                            })
                    }
                },
            )
        },
        expanded = false,
        onExpandedChange = { },
        content = { },
    )
}
