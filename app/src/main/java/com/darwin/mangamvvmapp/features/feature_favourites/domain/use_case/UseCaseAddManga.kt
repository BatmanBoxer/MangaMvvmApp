package com.darwin.mangamvvmapp.features.feature_favourites.domain.use_case

import com.darwin.mangamvvmapp.features.feature_favourites.domain.model.FavouritesResult
import com.darwin.mangamvvmapp.features.feature_favourites.domain.model.toFavouritesEntity
import com.darwin.mangamvvmapp.features.feature_favourites.domain.repository.FavouritesRepository
import javax.inject.Inject

class UseCaseAddManga @Inject constructor(
    private val repository: FavouritesRepository
) {
    suspend operator fun invoke(manga:FavouritesResult){
        repository.addFavouritesManga(manga.toFavouritesEntity())
    }
}