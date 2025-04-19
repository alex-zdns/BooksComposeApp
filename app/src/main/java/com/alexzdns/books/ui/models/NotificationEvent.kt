package com.alexzdns.books.ui.models

sealed interface NotificationEvent {
    data object AddToFavorites: NotificationEvent
    data object RemoveFromFavorites: NotificationEvent
}