package com.darwin.mangamvvmapp.features.feature_manga_search.presentation

import com.darwin.mangamvvmapp.features.feature_manga_search.domain.model.MangaSearchReasult

data class MangaSearchScreenState(
    var isLoading:Boolean = false,
    var mangaSearchResult: List<MangaSearchReasult> = emptyList(),
    var error:String = ""
)
