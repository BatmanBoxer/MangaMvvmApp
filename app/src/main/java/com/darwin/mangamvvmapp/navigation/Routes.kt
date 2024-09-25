package com.darwin.mangamvvmapp.navigation

import kotlinx.serialization.Serializable

@Serializable
object NavMangaSearchScreen

@Serializable
data class NavMangaReaderScreen(
    var url:String
)