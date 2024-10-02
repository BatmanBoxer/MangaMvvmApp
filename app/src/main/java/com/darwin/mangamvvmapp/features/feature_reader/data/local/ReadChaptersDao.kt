package com.darwin.mangamvvmapp.features.feature_reader.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface ReadChaptersDao {

    @Upsert
    suspend fun upsertReadChapters(readChaptersEntity: ReadChaptersEntity)

    @Query("SELECT * FROM readchaptersentity WHERE url = :url LIMIT 1")
    suspend fun getChapterByUrl(url:String):ReadChaptersEntity?
}