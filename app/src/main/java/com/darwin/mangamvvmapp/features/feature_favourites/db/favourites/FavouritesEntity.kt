package com.darwin.mangamvvmapp.features.feature_favourites.db.favourites

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.darwin.mangamvvmapp.features.feature_favourites.domain.model.FavouritesResult

@Entity
data class FavouritesEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int? = null,
    val url:String,
    val imgUrl:String,
    val name:String
)
fun FavouritesEntity.toFavouriteResult():FavouritesResult{
    return FavouritesResult(
        id=id ?:0,
        url=url,
        imgUrl=imgUrl,
        name=name
    )
}
