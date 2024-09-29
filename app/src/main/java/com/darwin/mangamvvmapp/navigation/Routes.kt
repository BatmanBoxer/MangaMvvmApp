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

sealed class NavRoutes{
    object MangaSearch : NavRoutes() // For NavMangaSearchScreen
    data class MangaReader(val screen: NavMangaReaderScreen) : NavRoutes() // For NavMangaReaderScreen
    data class MangaInfo(val screen: NavMangaInfoScreen) : NavRoutes() // For NavMangaInfoScreen
    object MangaFavourites : NavRoutes()
}