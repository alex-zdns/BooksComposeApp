package com.alexzdns.books.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = DbContract.Favorites.TABLE_NAME,
    indices = [Index(DbContract.Favorites.COLUMN_NAME_BOOK_ID, unique = true)],
    foreignKeys = [
        ForeignKey(
            entity = BookEntity::class,
            parentColumns = arrayOf(DbContract.Books.COLUMN_NAME_ID),
            childColumns = arrayOf(DbContract.Favorites.COLUMN_NAME_BOOK_ID),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )]
)
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DbContract.Favorites.COLUMN_NAME_ID)
    val id: Int,

    @ColumnInfo(name = DbContract.Favorites.COLUMN_NAME_BOOK_ID)
    val bookId: String,

    @ColumnInfo(name = DbContract.Favorites.COLUMN_NAME_TIMESTAMP)
    val timestamp: Long
)