package com.darwin.mangamvvmapp.features.feature_manga_info.data.repository

import com.darwin.mangamvvmapp.features.feature_manga_info.data.remote.GetMangaInfoApi
import com.darwin.mangamvvmapp.features.feature_manga_info.data.remote.dto.toMangaInfoResult
import com.darwin.mangamvvmapp.features.feature_manga_info.domain.model.MangaInfoResult
import com.darwin.mangamvvmapp.features.feature_manga_info.domain.repository.GetMangaInfoRepository
import javax.inject.Inject

class GetMangaInfoRepositoryImpl @Inject constructor(
    private val api: GetMangaInfoApi
) : GetMangaInfoRepository {
    override suspend fun getMangaInfo(site: String, url: String): MangaInfoResult {
        return api.getMangaInfo(site = site, url = url).toMangaInfoResult()
    }

}