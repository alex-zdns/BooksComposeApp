package com.alexzdns.books.ui.features.search.list

import androidx.paging.PagingData
import com.alexzdns.books.ui.core.models.BookItemUi
import kotlinx.coroutines.flow.Flow

sealed interface BookSearchState {
    data object EmptyQuery : BookSearchState
    data object Loading : BookSearchState
    data object Error : BookSearchState
    data object EmptyResult : BookSearchState
    data class Result(val bookList: Flow<PagingData<BookItemUi>>) : BookSearchState
}
