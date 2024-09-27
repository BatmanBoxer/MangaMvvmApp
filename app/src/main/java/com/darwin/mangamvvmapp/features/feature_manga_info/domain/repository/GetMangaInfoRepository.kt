package com.darwin.mangamvvmapp.features.feature_manga_info.domain.repository

import com.darwin.mangamvvmapp.features.feature_manga_info.domain.model.MangaInfoResult


interface GetMangaInfoRepository {
    suspend fun getMangaInfo(site:String,url:String): MangaInfoResult
}