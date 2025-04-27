package com.alexzdns.books.ui.core.models

sealed interface NotificationEvent {
    data object AddToFavorites: NotificationEvent
    data object RemoveFromFavorites: NotificationEvent
}