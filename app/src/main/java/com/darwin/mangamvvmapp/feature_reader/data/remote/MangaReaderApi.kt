package com.darwin.mangamvvmapp.feature_reader.data.remote

import com.darwin.mangamvvmapp.feature_reader.data.remote.dto.MangaReaderDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MangaReaderApi {

    @GET("/{site}")
    suspend fun getMangapages(
        @Path("site") site: String,  // Dynamic part of the URL
        @Query("image") name: String
    ): List<MangaReaderDto>



}