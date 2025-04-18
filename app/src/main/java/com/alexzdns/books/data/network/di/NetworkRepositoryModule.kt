package com.alexzdns.books.data.network.di

import com.alexzdns.books.data.network.repository.BookRepositoryImpl
import com.alexzdns.books.domain.repository.BookRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkRepositoryModule {
    @Binds
    abstract fun bindBookRepository(repository: BookRepositoryImpl): BookRepository
}