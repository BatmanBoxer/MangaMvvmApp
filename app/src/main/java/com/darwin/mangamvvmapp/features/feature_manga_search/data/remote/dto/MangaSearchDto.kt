package com.darwin.mangamvvmapp.features.feature_manga_search.data.remote.dto

import com.darwin.mangamvvmapp.commons.Constants
import com.darwin.mangamvvmapp.features.feature_manga_search.domain.model.MangaSearchReasult

data class MangaSearchDto(
    val id: Int,
    val img: String,
    val link: String,
    val title: String
)
fun MangaSearchDto.toMangaSearchReasult(): MangaSearchReasult {
    return MangaSearchReasult(
        img = img,
        link = link,
        title = title,
        path = if (link.contains("chapmanganato")) Constants.MANGANATO else Constants.KUNMANGA
    )
}