package com.darwin.mangamvvmapp.features.feature_favourites.db.favourites

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface FavouritesDao {

    @Upsert
    suspend fun upsertFavouritesManga(favouritesEntity: FavouritesEntity)

    @Query("DELETE FROM favouritesentity WHERE url = :url")
    suspend fun deleteFavouritesMangaByUrl(url: String)

    @Query("SELECT * FROM favouritesentity")
    suspend fun getFavouritesMangas():List<FavouritesEntity>

    @Query("SELECT * FROM favouritesentity WHERE url = :url LIMIT 1")
    suspend fun getFavouriteMangaByUrl(url: String): FavouritesEntity?
}