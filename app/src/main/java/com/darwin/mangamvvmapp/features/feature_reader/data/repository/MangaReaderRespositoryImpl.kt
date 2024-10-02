package com.darwin.mangamvvmapp.features.feature_reader.data.repository

import com.darwin.mangamvvmapp.features.feature_reader.data.local.ReadChaptersDao
import com.darwin.mangamvvmapp.features.feature_reader.data.local.ReadChaptersEntity
import com.darwin.mangamvvmapp.features.feature_reader.data.remote.MangaReaderApi
import com.darwin.mangamvvmapp.features.feature_reader.data.remote.dto.toMangaReaderResult
import com.darwin.mangamvvmapp.features.feature_reader.domain.model.MangaReaderReasult
import com.darwin.mangamvvmapp.features.feature_reader.domain.repository.MangaReaderRepository
import javax.inject.Inject

class MangaReaderRespositoryImpl @Inject constructor(
    private val api: MangaReaderApi,
    private val dao:ReadChaptersDao
): MangaReaderRepository {
    override suspend fun getMangaPages(path:String,url:String): List<MangaReaderReasult> {
        return api.getMangapages(path,url).map { it.toMangaReaderResult() }
    }

    override suspend fun getChaptersByUrl(url: String): ReadChaptersEntity {
        return dao.getChapterByUrl(url) ?: ReadChaptersEntity(url="", chapters = emptyList())
    }

    override suspend fun upsertChapter(readChaptersEntity: ReadChaptersEntity) {
        dao.upsertReadChapters(readChaptersEntity)
    }

}