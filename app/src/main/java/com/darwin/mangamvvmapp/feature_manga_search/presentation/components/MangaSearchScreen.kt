package com.darwin.mangamvvmapp.feature_manga_search.presentation.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.darwin.mangamvvmapp.feature_manga_search.presentation.MangaSearchViewModel
import com.darwin.mangamvvmapp.navigation.NavMangaReaderScreen

@Composable
fun MangaSearchScreen(
    navController: NavController,
    viewModel: MangaSearchViewModel = hiltViewModel<MangaSearchViewModel>()

){
    Box(modifier = Modifier.fillMaxSize()){
        LazyColumn {
            items(viewModel.state.value?.mangaSearchResult!!){item ->
                Text(item.title, modifier = Modifier.clickable {
                    Log.d("batman","link${item.link}")
                    navController.navigate(NavMangaReaderScreen(
                        url = item.link
                    ))
                })
            }
        }

    }

}
//Column {
//    Button(onClick = {
//        viewModel.search()
//    }) {
//        Text("Search Screen")
//    }
//    Button(onClick = {
//        navController.navigate(NavMangaReaderScreen(
//            url = "https://chapmanganato.to/manga-ng952689/chapter-700.5"
//        ))
//    }) { }
//}