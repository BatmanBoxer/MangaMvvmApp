package com.darwin.mangamvvmapp.navigation

import kotlinx.serialization.Serializable

@Serializable
object NavMangaSearchScreen

@Serializable
data class NavMangaReaderScreen(
    val urls:List<String>,
    val path:String,
    val index:Int
)

@Serializable
data class NavMangaInfoScreen(
    val url:String,
    val path:String,
)

@Serializable
object NavMangaFavouritesScreen