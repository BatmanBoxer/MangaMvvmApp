package com.darwin.mangamvvmapp.features.feature_favourites.domain.repository

import com.darwin.mangamvvmapp.features.feature_favourites.db.favourites.FavouritesEntity
import com.darwin.mangamvvmapp.features.feature_favourites.domain.model.FavouritesResult

interface FavouritesRepository {
    suspend fun getFavouritesManga():List<FavouritesResult>
    suspend fun addFavouritesManga(favouritesEntity: FavouritesEntity)
    suspend fun deleteFavouritesManga(url:String)
    suspend fun getFavouriteMangaByUrl(url:String):FavouritesResult
}