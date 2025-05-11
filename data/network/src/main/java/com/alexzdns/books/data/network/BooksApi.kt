package com.alexzdns.books.data.network

import com.alexzdns.books.data.network.models.BookItemDTO
import com.alexzdns.books.data.network.models.BooksListDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface BooksApi {
    @GET("volumes")
    suspend fun searchBooksList(
        @Query("q") searchQuery: String,
        @Query("orderBy") orderBy: String?,
    ): BooksListDTO

    @GET("volumes/{volumeId}")
    suspend fun getBookByVolumeId(
        @Path("volumeId") volumeId: String,
    ): BookItemDTO
}