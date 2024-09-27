package com.darwin.mangamvvmapp.features.feature_manga_info.data.remote

import com.darwin.mangamvvmapp.features.feature_manga_info.data.remote.dto.MangaInfoDto
import com.darwin.mangamvvmapp.features.feature_reader.data.remote.dto.MangaReaderDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GetMangaInfoApi {
    @GET("/{site}")
    suspend fun getMangaInfo(
        @Path("site") site: String,
        @Query("info") url: String
    ): MangaInfoDto

}