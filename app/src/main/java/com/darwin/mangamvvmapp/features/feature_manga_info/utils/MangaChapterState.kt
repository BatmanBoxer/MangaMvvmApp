package com.darwin.mangamvvmapp.features.feature_manga_info.utils

import com.darwin.mangamvvmapp.features.feature_manga_info.domain.model.ChapterResult

data class MangaChapterState(
    val chapterResult: ChapterResult,
    val isSelected:Boolean
)
