package com.alexzdns.books.data.network

import com.alexzdns.books.data.network.models.BookItemDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface BooksApi {
    @GET("volumes/{volumeId}")
    suspend fun getBookByVolumeId(
        @Path("volumeId") volumeId: String,
    ): BookItemDTO
}