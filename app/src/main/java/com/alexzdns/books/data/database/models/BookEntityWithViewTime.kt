package com.alexzdns.books.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Embedded

data class BookEntityWithViewTime(
    @Embedded
    val book: BookEntity,

    @ColumnInfo(DbContract.History.COLUMN_NAME_TIMESTAMP)
    val timestamp: Long,
)