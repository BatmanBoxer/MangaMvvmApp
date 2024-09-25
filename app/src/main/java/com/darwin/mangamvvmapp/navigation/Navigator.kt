package com.darwin.mangamvvmapp.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.darwin.mangamvvmapp.feature_manga_search.presentation.components.MangaSearchScreen
import com.darwin.mangamvvmapp.feature_reader.presentation.components.MangaReaderScreen


@Composable
fun Navigator(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavMangaSearchScreen
    ) {
        composable<NavMangaSearchScreen> {
            MangaSearchScreen(navController)
        }
        composable<NavMangaReaderScreen> {
            val args = it.toRoute<NavMangaReaderScreen>()
            MangaReaderScreen(args.url)
        }
    }
}