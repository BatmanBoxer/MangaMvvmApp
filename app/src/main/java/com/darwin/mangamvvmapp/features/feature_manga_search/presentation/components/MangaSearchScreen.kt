package com.darwin.mangamvvmapp.features.feature_manga_search.presentation.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.darwin.mangamvvmapp.features.feature_manga_search.presentation.MangaSearchViewModel
import com.darwin.mangamvvmapp.navigation.NavMangaInfoScreen
import com.darwin.mangamvvmapp.navigation.NavMangaReaderScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MangaSearchScreen(
    navController: NavController,
    viewModel: MangaSearchViewModel,
    scrollBehavior: TopAppBarScrollBehavior
) {
    if (viewModel.state.value?.isLoading == true){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            CircularProgressIndicator()
        }
    }
    if(viewModel.state.value?.isLoading == false){
        Box(modifier = Modifier.fillMaxSize()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
            ) {
                items(viewModel.state.value?.mangaSearchResult!!, key = {item -> item.img }) { item ->
                    ImageCard(
                        url = item.img,
                        contentDescription = item.title,
                        title = item.title,
                        modifier = Modifier.clickable {
                            navController.navigate(
                                NavMangaInfoScreen(
                                    url = item.link,
                                    path = item.path
                                )
                            )
                        }
                    )
                }
            }

        }
    }
    if (viewModel.state.value?.error != null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Text(viewModel.state.value!!.error)
        }
    }
    if (viewModel.state.value?.mangaSearchResult?.isEmpty() == true && viewModel.state.value?.isLoading == false && viewModel.state.value!!.error == ""){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
           Text("No Manga Found")
        }
    }
}