package com.alexzdns.books.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = DbContract.History.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = BookEntity::class,
            parentColumns = arrayOf(DbContract.Books.COLUMN_NAME_ID),
            childColumns = arrayOf(DbContract.History.COLUMN_NAME_BOOK_ID),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )]
)
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DbContract.History.COLUMN_NAME_ID)
    val id: Int,

    @ColumnInfo(name = DbContract.History.COLUMN_NAME_BOOK_ID)
    val bookId: String,

    @ColumnInfo(name = DbContract.History.COLUMN_NAME_TIMESTAMP)
    val timestamp: Long
)