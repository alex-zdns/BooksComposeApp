package com.alexzdns.books.ui.search

import com.alexzdns.books.ui.core.models.BookItemUi

sealed interface BookSearchState {
    data object EmptyQuery : BookSearchState
    data object Loading : BookSearchState
    data object Error : BookSearchState
    data object EmptyResult : BookSearchState
    data class Result(val bookList: List<BookItemUi>) : BookSearchState
}
