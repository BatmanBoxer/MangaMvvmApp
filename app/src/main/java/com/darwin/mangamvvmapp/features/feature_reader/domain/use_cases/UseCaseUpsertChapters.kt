package com.darwin.mangamvvmapp.features.feature_reader.domain.use_cases

import com.darwin.mangamvvmapp.features.feature_reader.data.local.ReadChaptersEntity
import com.darwin.mangamvvmapp.features.feature_reader.domain.repository.MangaReaderRepository
import javax.inject.Inject

class UseCaseUpsertChapters @Inject constructor(
    private val repository: MangaReaderRepository
) {
    suspend operator fun invoke(readChaptersEntity: ReadChaptersEntity){
        repository.upsertChapter(readChaptersEntity)
    }
}