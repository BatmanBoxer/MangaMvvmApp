package com.darwin.mangamvvmapp.feature_reader.data.repository

import com.darwin.mangamvvmapp.feature_reader.data.remote.MangaReaderApi
import com.darwin.mangamvvmapp.feature_reader.data.remote.dto.toMangaReaderResult
import com.darwin.mangamvvmapp.feature_reader.domain.model.MangaReaderReasult
import com.darwin.mangamvvmapp.feature_reader.domain.repository.MangaReaderRepository
import javax.inject.Inject

class MangaReaderRespositoryImpl @Inject constructor(
    private val api:MangaReaderApi
):MangaReaderRepository {
    override suspend fun getMangaPages(path:String,url:String): List<MangaReaderReasult> {
        return api.getMangapages(path,url).map { it.toMangaReaderResult() }
    }

}