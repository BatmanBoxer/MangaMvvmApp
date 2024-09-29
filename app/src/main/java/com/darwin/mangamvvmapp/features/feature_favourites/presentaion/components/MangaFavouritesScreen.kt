package com.darwin.mangamvvmapp.features.feature_favourites.presentaion.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.darwin.mangamvvmapp.commons.Constants
import com.darwin.mangamvvmapp.features.feature_favourites.presentaion.MangaFavouritesViewModel
import com.darwin.mangamvvmapp.navigation.NavMangaFavouritesScreen
import com.darwin.mangamvvmapp.navigation.NavMangaInfoScreen

@Composable
fun MangaFavouritesScreen(
    navController: NavController,
    viewModel: MangaFavouritesViewModel = hiltViewModel<MangaFavouritesViewModel>()
) {
    Column {
        viewModel.favMangas.map {
            Text(it.name, modifier = Modifier.clickable {
                navController.navigate(
                    NavMangaInfoScreen(
                        path = Constants.MANGANATO,
                        url = it.url
                    )
                )
            })
        }
    }
}