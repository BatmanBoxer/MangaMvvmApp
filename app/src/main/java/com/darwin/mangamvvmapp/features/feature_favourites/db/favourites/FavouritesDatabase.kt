package com.darwin.mangamvvmapp.features.feature_favourites.db.favourites

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FavouritesEntity::class],
    version = 1
)abstract class FavouritesDatabase:RoomDatabase() {
    abstract val dao: FavouritesDao
}