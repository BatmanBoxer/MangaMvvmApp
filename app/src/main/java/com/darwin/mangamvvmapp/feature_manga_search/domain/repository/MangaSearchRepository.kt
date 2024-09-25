package com.darwin.mangamvvmapp.feature_manga_search.domain.repository

import com.darwin.mangamvvmapp.feature_manga_search.domain.model.MangaSearchReasult

interface MangaSearchRepository {
    suspend fun getMangas(path:String,name:String):List<MangaSearchReasult>

}