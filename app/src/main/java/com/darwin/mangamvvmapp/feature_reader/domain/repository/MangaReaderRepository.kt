package com.darwin.mangamvvmapp.feature_reader.domain.repository

import com.darwin.mangamvvmapp.feature_reader.domain.model.MangaReaderReasult

interface MangaReaderRepository {
    suspend fun getMangaPages(path:String,url:String):List<MangaReaderReasult>
}