package com.alexzdns.books.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.alexzdns.books.data.database.models.HistoryEntity

@Dao
interface HistoryBooksDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(book: HistoryEntity)
}