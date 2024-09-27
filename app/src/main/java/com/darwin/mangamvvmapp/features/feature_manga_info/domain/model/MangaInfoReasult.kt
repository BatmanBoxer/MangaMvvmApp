package com.darwin.mangamvvmapp.features.feature_manga_info.domain.model


data class MangaInfoResult(
    val chapters: List<ChapterResult>,
    val description: String,
    val genres: List<String>,
    val img: String,
    val rating: String,
    val title: String
)