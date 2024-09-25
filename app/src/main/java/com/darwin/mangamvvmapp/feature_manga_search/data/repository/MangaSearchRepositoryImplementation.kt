package com.darwin.mangamvvmapp.feature_manga_search.data.repository

import com.darwin.mangamvvmapp.feature_manga_search.data.remote.MangaSearchApi
import com.darwin.mangamvvmapp.feature_manga_search.data.remote.dto.toMangaSearchReasult
import com.darwin.mangamvvmapp.feature_manga_search.domain.model.MangaSearchReasult
import com.darwin.mangamvvmapp.feature_manga_search.domain.repository.MangaSearchRepository
import javax.inject.Inject

class MangaSearchRepositoryImplementation @Inject constructor(
    private val api:MangaSearchApi
):MangaSearchRepository{
    override suspend fun getMangas(path:String,name: String): List<MangaSearchReasult> {
        return api.getManga(path,name).map { it.toMangaSearchReasult() };
    }

}