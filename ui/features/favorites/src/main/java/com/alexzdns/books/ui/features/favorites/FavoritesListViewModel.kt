package com.alexzdns.books.ui.features.favorites

import androidx.lifecycle.ViewModel
import com.alexzdns.books.domain.repository.BookCacheRepository
import com.alexzdns.books.ui.core.models.BookItemUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class FavoritesListViewModel @Inject constructor(
    bookRepository: BookCacheRepository
) : ViewModel() {

    val favoriteBooksStateFlow = bookRepository.getAllFavoritesBooks().map { list ->
        list.map {
            BookItemUi(
                book = it,
                isFavorite = true,
            )
        }
    }
}

