package com.darwin.mangamvvmapp.navigation

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.darwin.mangamvvmapp.features.feature_favourites.presentaion.components.MangaFavouritesScreen
import com.darwin.mangamvvmapp.features.feature_manga_info.presentation.MangaInfoViewModel
import com.darwin.mangamvvmapp.features.feature_manga_info.presentation.components.FavouritesTopAppBar
import com.darwin.mangamvvmapp.features.feature_manga_info.presentation.components.InfoTopAppBar
import com.darwin.mangamvvmapp.features.feature_manga_info.presentation.components.MangaInfoScreen
import com.darwin.mangamvvmapp.features.feature_manga_search.presentation.MangaSearchViewModel
import com.darwin.mangamvvmapp.features.feature_manga_search.presentation.components.MangaSearchScreen
import com.darwin.mangamvvmapp.features.feature_manga_search.presentation.components.SearchTopAppBar
import com.darwin.mangamvvmapp.features.feature_reader.presentation.components.MangaReaderScreen
import com.darwin.mangamvvmapp.navigation.utils.bottomItems


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("RestrictedApi", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Navigator(
    mangaSearchViewModel: MangaSearchViewModel = hiltViewModel(),
    ) {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()


    Scaffold(
        topBar = {
            if (currentDestination?.route?.contains("NavMangaSearchScreen") == true) {
                SearchTopAppBar(mangaSearchViewModel,scrollBehavior)
            }
            if (currentDestination?.route?.contains("NavMangaInfoScreen") == true) {
                InfoTopAppBar( navController, scrollBehavior)
            }
            if (currentDestination?.route?.contains("NavMangaFavouritesScreen") == true) {
                FavouritesTopAppBar(scrollBehavior)
            }

        },
        bottomBar = {
            if (currentDestination?.route?.contains("NavMangaReaderScreen") == false
                && currentDestination.route?.contains("NavMangaInfoScreen") == false
            ) {
                NavigationBar() {
                    bottomItems().forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = selectedItemIndex == index,
                            onClick = {
                                selectedItemIndex = index
                                when (index) {
                                    0 -> navController.navigate(NavMangaSearchScreen)
                                    1 -> {}
                                    2 -> navController.navigate(NavMangaFavouritesScreen)
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = if (index == selectedItemIndex) {
                                        item.selectedIcon
                                    } else {
                                        item.unselectedIcon
                                    },
                                    contentDescription = "",
                                )
                            },
                            label = {
                                Text(item.title)
                            }
                        )
                    }

                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            enterTransition = {
                EnterTransition.None
            },
            startDestination = NavMangaSearchScreen,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<NavMangaSearchScreen> {
              MangaSearchScreen(navController, mangaSearchViewModel,scrollBehavior)

            }
            composable<NavMangaReaderScreen>(
                exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(700)) },
                popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(700)) },
                popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(700)) },

            ) {
                val args = it.toRoute<NavMangaReaderScreen>()
                MangaReaderScreen(args)
            }
            composable<NavMangaInfoScreen>(
                enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(700)) },
                exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(400)) },
                popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(700)) },
                popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(400)) },
            ) {
                val args = it.toRoute<NavMangaInfoScreen>()
                MangaInfoScreen(
                    navController = navController,
                    scrollBehavior = scrollBehavior,

                )
            }
            composable<NavMangaFavouritesScreen> {
                MangaFavouritesScreen(scrollBehavior,navController)
            }
        }

    }

}