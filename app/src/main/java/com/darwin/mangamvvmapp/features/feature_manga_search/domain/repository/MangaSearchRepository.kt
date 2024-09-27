package com.darwin.mangamvvmapp.features.feature_manga_search.domain.repository

import com.darwin.mangamvvmapp.features.feature_manga_search.domain.model.MangaSearchReasult

interface MangaSearchRepository {
    suspend fun getMangas(path:String,name:String):List<MangaSearchReasult>

}