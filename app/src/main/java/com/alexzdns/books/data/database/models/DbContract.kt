package com.alexzdns.books.data.database.models

import android.provider.BaseColumns

object DbContract {
    const val DATABASE_NAME = "BooksApp.db"

    object Books {
        const val TABLE_NAME = "books"

        const val COLUMN_NAME_ID = BaseColumns._ID
        const val COLUMN_NAME_TITLE = "title"
        const val COLUMN_NAME_DESCRIPTION = "description"
        const val COLUMN_NAME_PUBLISHED_YEAR = "published_year"
        const val COLUMN_NAME_THUMBNAIL_URL = "thumbnail_url"
        const val COLUMN_NAME_IMAGE_URL = "image_url"
        const val COLUMN_NAME_AUTHORS = "authors"
    }

    object Favorites {
        const val TABLE_NAME = "favorites"

        const val COLUMN_NAME_ID = BaseColumns._ID
        const val COLUMN_NAME_BOOK_ID = "book_id"
        const val COLUMN_NAME_TIMESTAMP = "timestamp"
    }
}