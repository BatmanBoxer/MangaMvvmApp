package com.darwin.mangamvvmapp.features.feature_reader.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.darwin.mangamvvmapp.features.feature_favourites.db.favourites.FavouritesEntity

@Database(
    entities = [ ReadChaptersEntity::class], // Include ReadChaptersEntity
    version = 2
)
@TypeConverters(Converters::class)
abstract class ReadChapterDatabase : RoomDatabase() {
    abstract val dao: ReadChaptersDao
}