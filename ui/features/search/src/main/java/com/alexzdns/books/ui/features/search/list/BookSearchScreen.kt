package com.alexzdns.books.ui.features.search.list

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alexzdns.books.domain.models.BookSortType
import com.alexzdns.books.ui.common.favorites.FavoritesOperationViewModel
import com.alexzdns.books.ui.common.list.BooksListView
import com.alexzdns.books.ui.core.theme.blue
import com.alexzdns.books.ui.core.theme.lightGrey
import com.alexzdns.books.ui.core.views.ErrorView
import com.alexzdns.books.ui.core.views.LoaderView
import com.alexzdns.books.ui.core.views.MessageView
import com.alexzdns.books.ui.features.search.R

@Composable
fun BookSearchScreen(
    onBookClick: (String) -> Unit = {},
    onFilterClick: (type: BookSortType?) -> Unit = {},
    viewModel: BookSearchViewModel,
) {
    val queryState = viewModel.searchQuery.collectAsStateWithLifecycle()
    val screenState =
        viewModel.booksStateFlow.collectAsStateWithLifecycle(BookSearchState.EmptyQuery)

    val favoritesOperationViewModel: FavoritesOperationViewModel =
        hiltViewModel(LocalActivity.current as ViewModelStoreOwner)

    Column {
        Toolbar(
            queryState.value,
            viewModel::onQueryChange,
            onFilterClick = {
                onFilterClick.invoke(viewModel.bookSortType)
            }
        )

        when (val result = screenState.value) {
            BookSearchState.EmptyQuery -> MessageView(R.string.empty_query_message)
            BookSearchState.EmptyResult -> MessageView(R.string.empty_result_message)
            BookSearchState.Error -> ErrorView(viewModel::retrySearch)
            BookSearchState.Loading -> LoaderView()
            is BookSearchState.Result -> BooksListView(
                books = result.bookList,
                onBookClick = onBookClick,
                onFavorite = favoritesOperationViewModel::onFavoriteClick
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Toolbar(
    query: String,
    onQueryChange: (String) -> Unit,
    onFilterClick: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Row(Modifier.fillMaxWidth()) {
        SearchBar(
            modifier = Modifier
                .padding(top = 8.dp, start = 20.dp, bottom = 12.dp)
                .weight(1.0f),
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
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = Color(0xFFF7F7F7),
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 8.dp, end = 20.dp)
                .clickable(onClick = onFilterClick)
                .wrapContentWidth(),
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_filter_24),
                tint = blue,
                contentDescription = null,
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}
