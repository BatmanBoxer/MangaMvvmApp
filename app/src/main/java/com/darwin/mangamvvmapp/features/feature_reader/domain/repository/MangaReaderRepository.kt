package com.darwin.mangamvvmapp.features.feature_reader.domain.repository

import com.darwin.mangamvvmapp.features.feature_reader.data.local.ReadChaptersEntity
import com.darwin.mangamvvmapp.features.feature_reader.domain.model.MangaReaderReasult

interface MangaReaderRepository {
    suspend fun getMangaPages(path:String,url:String):List<MangaReaderReasult>
    suspend fun getChaptersByUrl(url: String):ReadChaptersEntity
    suspend fun upsertChapter(readChaptersEntity: ReadChaptersEntity)
}