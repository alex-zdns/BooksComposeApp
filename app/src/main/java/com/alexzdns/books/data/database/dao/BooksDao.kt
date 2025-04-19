package com.alexzdns.books.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alexzdns.books.data.database.models.BookEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BooksDao {
    @Query("SELECT * FROM books WHERE _id = :bookId")
    suspend fun loadById(bookId: String): BookEntity?

    @Query("SELECT * FROM books WHERE _id IN (SELECT book_id FROM FAVORITES)")
    fun getFavoritesBook(): Flow<List<BookEntity>>

    @Insert(onConflict = OnConflictStrategy.NONE)
    suspend fun insert(book: BookEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(book: List<BookEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun replace(book: BookEntity)

    @Query("DELETE FROM books WHERE _id = :bookId")
    suspend fun deleteById(bookId: String)
}