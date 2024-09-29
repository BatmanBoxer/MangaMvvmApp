package com.darwin.mangamvvmapp.features.feature_favourites.data.repository

import com.darwin.mangamvvmapp.features.feature_favourites.db.favourites.FavouritesDao
import com.darwin.mangamvvmapp.features.feature_favourites.db.favourites.FavouritesEntity
import com.darwin.mangamvvmapp.features.feature_favourites.db.favourites.toFavouriteResult
import com.darwin.mangamvvmapp.features.feature_favourites.domain.model.FavouritesResult
import com.darwin.mangamvvmapp.features.feature_favourites.domain.repository.FavouritesRepository
import javax.inject.Inject

class FavouritesRepositoryImpl @Inject constructor(
    private val dao: FavouritesDao
) :FavouritesRepository{
    override suspend fun getFavouritesManga(): List<FavouritesResult> {
      return dao.getFavouritesMangas() .map { it.toFavouriteResult() }
    }

    override suspend fun deleteFavouritesManga(url: String) {
        dao.deleteFavouritesMangaByUrl(url = url)
    }

    override suspend fun getFavouriteMangaByUrl(url: String): FavouritesResult {
        return dao.getFavouriteMangaByUrl(url)!!.toFavouriteResult()
    }

    override suspend fun addFavouritesManga(favouritesEntity: FavouritesEntity) {
        dao.upsertFavouritesManga(favouritesEntity=favouritesEntity)
    }
}