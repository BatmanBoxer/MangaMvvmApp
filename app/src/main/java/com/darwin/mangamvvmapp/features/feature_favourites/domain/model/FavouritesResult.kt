package com.darwin.mangamvvmapp.features.feature_favourites.domain.model

import com.darwin.mangamvvmapp.features.feature_favourites.db.favourites.FavouritesEntity

data class FavouritesResult(
    val id:Int = 0,
    val url:String,
    val imgUrl:String,
    val name:String
)
fun FavouritesResult.toFavouritesEntity(): FavouritesEntity {
    return FavouritesEntity(
        id=id,
        url=url,
        imgUrl=imgUrl,
        name=name
    )
}
