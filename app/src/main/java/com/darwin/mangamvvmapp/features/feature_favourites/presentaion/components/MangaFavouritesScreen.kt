package com.darwin.mangamvvmapp.features.feature_favourites.presentaion.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.darwin.mangamvvmapp.features.feature_favourites.presentaion.MangaFavouritesViewModel
import com.darwin.mangamvvmapp.navigation.NavMangaFavouritesScreen

@Composable
fun MangaFavouritesScreen(
     viewModel: MangaFavouritesViewModel = hiltViewModel<MangaFavouritesViewModel>()
){
    viewModel.test()
    Text("manga favourites screen")
}