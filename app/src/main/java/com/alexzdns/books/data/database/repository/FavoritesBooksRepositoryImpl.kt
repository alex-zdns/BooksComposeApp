package com.alexzdns.books.data.database.repository

import com.alexzdns.books.data.database.dao.FavoritesBooksDao
import com.alexzdns.books.data.database.mappers.FavoritesBooksDbMapper
import com.alexzdns.books.domain.repository.FavoritesBooksRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoritesBooksRepositoryImpl @Inject constructor(
    private val favoritesBooksDao: FavoritesBooksDao,
    private val favoritesBooksDbMapper: FavoritesBooksDbMapper
) : FavoritesBooksRepository {
    override fun isFavorites(bookId: String): Flow<Boolean> =
        favoritesBooksDao.isFavorite(bookId)

    override suspend fun addToFavorites(bookId: String) {
        favoritesBooksDao.insert(favoritesBooksDbMapper.toEntity(bookId))
    }

    override suspend fun removeToFavorites(bookId: String) {
        favoritesBooksDao.deleteByBookId(bookId)
    }
}
