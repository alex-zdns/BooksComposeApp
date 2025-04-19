package com.alexzdns.books.ui.favorite

import androidx.lifecycle.ViewModel
import com.alexzdns.books.domain.repository.BookCacheRepository
import com.alexzdns.books.ui.models.BookItemUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val bookRepository: BookCacheRepository
) : ViewModel() {

    val favoriteBooksStateFlow = bookRepository.getAllFavoritesBooks().map { list ->
        list.map {
            BookItemUi(
                book = it,
                isFavorite = true,
            )
        }
    }

    fun onFavoriteClick(bookId: String) {

    }
}

