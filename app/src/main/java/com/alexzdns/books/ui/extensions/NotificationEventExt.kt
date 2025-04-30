package com.alexzdns.books.ui.extensions

import android.content.Context
import androidx.annotation.StringRes
import com.alexzdns.books.ui.core.models.NotificationEvent
import com.alexzdns.books.ui.core.models.SnackBarBookVisuals
import com.alexzdns.books.ui.core.theme.blue
import com.alexzdns.books.ui.core.theme.darkGrey
import com.alexzdns.books.ui.common.favorites.R as FavoritesCoreR

fun NotificationEvent.toSnackBarVisual(context: Context): SnackBarBookVisuals {
    return SnackBarBookVisuals(
        message = context.getString(this.getMessage()),
        color = this.getBackgroundColor()
    )
}

@StringRes
fun NotificationEvent.getMessage(): Int {
    return when (this) {
        NotificationEvent.AddToFavorites -> FavoritesCoreR.string.add_book_to_favorite_message
        NotificationEvent.RemoveFromFavorites -> FavoritesCoreR.string.remove_book_from_favorite_message
    }
}

fun NotificationEvent.getBackgroundColor() = when (this) {
    NotificationEvent.AddToFavorites -> blue
    NotificationEvent.RemoveFromFavorites -> darkGrey
}
