package com.darwin.mangamvvmapp.features.feature_manga_info.data.remote.dto

import com.darwin.mangamvvmapp.features.feature_manga_info.domain.model.ChapterResult

data class ChapterDto(
    val title: String,
    val url: String
)
fun ChapterDto.toChapterResult(): ChapterResult {
    return ChapterResult(
        title=title,
        url=url)
}