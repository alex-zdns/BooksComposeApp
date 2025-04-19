package com.alexzdns.books.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alexzdns.books.data.database.dao.BooksDao
import com.alexzdns.books.data.database.dao.FavoritesBooksDao
import com.alexzdns.books.data.database.dao.HistoryBooksDao
import com.alexzdns.books.data.database.models.BookEntity
import com.alexzdns.books.data.database.models.FavoriteEntity
import com.alexzdns.books.data.database.models.HistoryEntity

@Database(
    entities = [BookEntity::class, FavoriteEntity::class, HistoryEntity::class],
    version = 1
)
abstract class BooksAppDatabase : RoomDatabase() {
    abstract val booksDao: BooksDao
    abstract val favoritesBooksDao: FavoritesBooksDao
    abstract val historyBooksDao: HistoryBooksDao
}