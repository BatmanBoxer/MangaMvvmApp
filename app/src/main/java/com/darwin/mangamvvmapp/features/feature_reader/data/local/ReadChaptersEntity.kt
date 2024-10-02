package com.darwin.mangamvvmapp.features.feature_reader.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ReadChaptersEntity(
    @PrimaryKey(autoGenerate = false)
    val url:String,
    val chapters:List<String>
)
