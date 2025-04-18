package com.alexzdns.books.ui.search

import com.alexzdns.books.domain.models.BookItem

sealed interface BookSearchState {
    data object EmptyQuery : BookSearchState
    data object Loading : BookSearchState
    data object Error : BookSearchState
    data object EmptyResult : BookSearchState
    data class Result(val bookList: List<BookItem>) : BookSearchState
}
