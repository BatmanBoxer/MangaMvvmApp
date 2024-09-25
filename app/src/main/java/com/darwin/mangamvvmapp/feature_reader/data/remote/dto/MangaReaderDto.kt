package com.darwin.mangamvvmapp.feature_reader.data.remote.dto

import com.darwin.mangamvvmapp.feature_reader.domain.model.MangaReaderReasult

data class MangaReaderDto(
    val id: Int,
    val img: String
)
fun MangaReaderDto.toMangaReaderResult():MangaReaderReasult{
   return  MangaReaderReasult(
        img = img
    )
}