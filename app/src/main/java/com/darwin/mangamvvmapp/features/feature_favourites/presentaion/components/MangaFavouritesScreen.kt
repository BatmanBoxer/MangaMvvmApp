package com.darwin.mangamvvmapp.features.feature_favourites.presentaion.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.darwin.mangamvvmapp.commons.Constants
import com.darwin.mangamvvmapp.features.feature_favourites.presentaion.MangaFavouritesViewModel
import com.darwin.mangamvvmapp.features.feature_manga_search.presentation.components.ImageCard
import com.darwin.mangamvvmapp.navigation.NavMangaFavouritesScreen
import com.darwin.mangamvvmapp.navigation.NavMangaInfoScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MangaFavouritesScreen(
    scrollBehavior: TopAppBarScrollBehavior,
    navController: NavController,
    viewModel: MangaFavouritesViewModel = hiltViewModel<MangaFavouritesViewModel>()
) {
    Column {
        Box(modifier = Modifier.fillMaxSize()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
            ) {
                items(viewModel.favMangas) { item ->
                    ImageCard(
                        url = item.imgUrl,
                        contentDescription = item.name,
                        title = item.name,
                        modifier = Modifier.clickable {
                            navController.navigate(
                                NavMangaInfoScreen(
                                    url = item.url,
                                    path = if (item.url.contains(Constants.MANGANATO)) Constants.MANGANATO else if (item.url.contains(
                                            Constants.KUNMANGA
                                        )
                                    ) Constants.KUNMANGA else "none"
                                )
                            )
                        }
                    )
                }
            }

        }
    }
}