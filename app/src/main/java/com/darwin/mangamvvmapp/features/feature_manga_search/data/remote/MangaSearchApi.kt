package com.darwin.mangamvvmapp.features.feature_manga_search.data.remote

import com.darwin.mangamvvmapp.features.feature_manga_search.data.remote.dto.MangaSearchDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MangaSearchApi{
    @GET("/{site}")
    suspend fun getManga(
        @Path("site") path: String,
        @Query("name") name: String
    ):List<MangaSearchDto>

}