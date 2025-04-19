package com.alexzdns.books.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alexzdns.books.data.database.models.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesBooksDao {
    @Query("SELECT EXISTS(SELECT * FROM favorites WHERE book_id = :bookId)")
    fun isFavorite(bookId: String): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(book: FavoriteEntity)

    @Query("DELETE FROM favorites WHERE book_id = :bookId")
    suspend fun deleteByBookId(bookId: String)
}