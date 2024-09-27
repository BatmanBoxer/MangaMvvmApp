package com.darwin.mangamvvmapp.features.feature_manga_info.data.remote.dto

import com.darwin.mangamvvmapp.features.feature_manga_info.domain.model.MangaInfoResult

data class MangaInfoDto(
    val chapters: List<ChapterDto>,
    val description: String,
    val genres: List<String>,
    val img: String,
    val rating: String,
    val title: String
)
fun MangaInfoDto.toMangaInfoResult(): MangaInfoResult {
    return MangaInfoResult(
        chapters = chapters.map { it.toChapterResult() },
        description = description,
        genres = genres,
        img = img,
        rating = rating,
        title = title
    )
}