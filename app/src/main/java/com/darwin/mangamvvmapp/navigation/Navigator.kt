package com.darwin.mangamvvmapp.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.darwin.mangamvvmapp.features.feature_favourites.presentaion.components.MangaFavouritesScreen
import com.darwin.mangamvvmapp.features.feature_manga_info.presentation.components.MangaInfoScreen
import com.darwin.mangamvvmapp.features.feature_manga_search.presentation.components.MangaSearchScreen
import com.darwin.mangamvvmapp.features.feature_reader.presentation.components.MangaReaderScreen
import com.darwin.mangamvvmapp.navigation.utils.BottomNavItem
import com.darwin.mangamvvmapp.navigation.utils.bottomItems


@SuppressLint("RestrictedApi", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Navigator() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }
    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = MaterialTheme.colorScheme.background) {
                bottomItems().forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = { selectedItemIndex = index },
                        icon = {
                            Icon(
                                imageVector = if (index == selectedItemIndex) {
                                    item.selectedIcon
                                } else {
                                    item.unselectedIcon
                                },
                                contentDescription = "",
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    )

                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavMangaSearchScreen,
            modifier = Modifier.padding(innerPadding)
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

    }

}