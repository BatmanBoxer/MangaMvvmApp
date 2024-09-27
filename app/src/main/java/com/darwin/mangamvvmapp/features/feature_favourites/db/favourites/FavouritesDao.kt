package com.darwin.mangamvvmapp.features.feature_favourites.db.favourites

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface FavouritesDao {

    @Upsert
    suspend fun upsertFavouritesManga(favouritesEntity: FavouritesEntity)

    @Delete
    suspend fun deleteFavouritesManga(favouritesEntity: FavouritesEntity)

    @Query("SELECT * FROM favouritesentity")
    suspend fun getFavouritesMangas():List<FavouritesEntity>

}