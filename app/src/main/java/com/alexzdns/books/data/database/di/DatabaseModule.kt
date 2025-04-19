package com.alexzdns.books.data.database.di

import android.content.Context
import androidx.room.Room
import com.alexzdns.books.data.database.BooksAppDatabase
import com.alexzdns.books.data.database.dao.BooksDao
import com.alexzdns.books.data.database.dao.FavoritesBooksDao
import com.alexzdns.books.data.database.models.DbContract
import com.alexzdns.books.data.database.repository.BookCacheRepositoryImpl
import com.alexzdns.books.data.database.repository.FavoritesBooksRepositoryImpl
import com.alexzdns.books.domain.repository.BookCacheRepository
import com.alexzdns.books.domain.repository.FavoritesBooksRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DatabaseModule {

    companion object {
        @Provides
        @Singleton
        fun provideDatabase(@ApplicationContext applicationContext: Context): BooksAppDatabase =
            Room.databaseBuilder(
                applicationContext,
                BooksAppDatabase::class.java,
                DbContract.DATABASE_NAME
            )
                .fallbackToDestructiveMigration(true)
                .build()

        @Provides
        fun provideBooksDao(database: BooksAppDatabase): BooksDao {
            return database.booksDao
        }

        @Provides
        fun provideFavoritesBooksDao(database: BooksAppDatabase): FavoritesBooksDao {
            return database.favoritesBooksDao
        }
    }

    @Binds
    abstract fun bindFavoritesBooksRepository(repository: FavoritesBooksRepositoryImpl): FavoritesBooksRepository

    @Binds
    abstract fun bindBookCacheRepository(repository: BookCacheRepositoryImpl): BookCacheRepository
}