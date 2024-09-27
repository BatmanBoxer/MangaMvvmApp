package com.darwin.mangamvvmapp.navigation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.darwin.mangamvvmapp.features.feature_favourites.presentaion.components.MangaFavouritesScreen
import com.darwin.mangamvvmapp.features.feature_manga_info.presentation.components.MangaInfoScreen
import com.darwin.mangamvvmapp.features.feature_manga_search.presentation.components.MangaSearchScreen
import com.darwin.mangamvvmapp.features.feature_reader.presentation.components.MangaReaderScreen


@SuppressLint("RestrictedApi")
@Composable
fun Navigator() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    if (currentDestination != null) {
        Log.d("batman","the route is ${currentDestination.route}")
    }
    Column {  // Log the current route


        NavHost(
            navController = navController,
            startDestination = NavMangaSearchScreen
        ) {
            composable<NavMangaSearchScreen> {
                MangaSearchScreen(navController)
            }
            composable<NavMangaReaderScreen> {
                val args = it.toRoute<NavMangaReaderScreen>()
                MangaReaderScreen(args)
            }
            composable<NavMangaInfoScreen> {
                MangaInfoScreen(navController)
            }
            composable<NavMangaFavouritesScreen> {
                MangaFavouritesScreen()
            }
        }
        Button(onClick = {
            navController.navigate(NavMangaFavouritesScreen)
        }) { }
    }
}