package com.alexzdns.books.ui.favorite.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexzdns.books.domain.repository.FavoritesBooksRepository
import com.alexzdns.books.ui.models.NotificationEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesOperationViewModel @Inject constructor(
    private val favoritesBooksRepository: FavoritesBooksRepository,
) : ViewModel() {

    private val _notificationSharedFlow = MutableSharedFlow<NotificationEvent>(
        replay = 0,
        extraBufferCapacity = 1,
    )
    val notificationSharedFlow = _notificationSharedFlow.asSharedFlow()

    fun onFavoriteClick(bookId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val isFavorite = favoritesBooksRepository.isFavorites(bookId)
            if (isFavorite) {
                favoritesBooksRepository.removeToFavorites(bookId)
                _notificationSharedFlow.emit(NotificationEvent.RemoveFromFavorites)
            } else {
                favoritesBooksRepository.addToFavorites(bookId)
                _notificationSharedFlow.emit(NotificationEvent.AddToFavorites)
            }
        }
    }
}
