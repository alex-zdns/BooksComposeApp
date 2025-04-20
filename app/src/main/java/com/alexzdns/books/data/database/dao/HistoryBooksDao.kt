package com.alexzdns.books.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alexzdns.books.data.database.models.HistoryEntity
import com.alexzdns.books.data.database.models.BookEntityWithViewTime
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryBooksDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(book: HistoryEntity)

    @Query("SELECT books.*, history.timestamp from books JOIN history ON books._id == history.book_id ORDER BY history.timestamp DESC")
    fun getBooksWithViewTime(): Flow<List<BookEntityWithViewTime>>
}