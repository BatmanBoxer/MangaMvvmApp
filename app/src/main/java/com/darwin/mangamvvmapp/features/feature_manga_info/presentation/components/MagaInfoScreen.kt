package com.darwin.mangamvvmapp.features.feature_manga_info.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.darwin.mangamvvmapp.commons.Constants
import com.darwin.mangamvvmapp.features.feature_manga_info.presentation.MangaInfoViewModel
import com.darwin.mangamvvmapp.navigation.NavMangaInfoScreen
import com.darwin.mangamvvmapp.navigation.NavMangaReaderScreen
import com.darwin.mangamvvmapp.navigation.NavMangaSearchScreen

@Composable
fun MangaInfoScreen(
    navController: NavController,
    viewModel: MangaInfoViewModel = hiltViewModel<MangaInfoViewModel>()
){
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        itemsIndexed(viewModel.chapters) { index, chapter ->
            Text(chapter, modifier = Modifier.clickable {
                navController.navigate(NavMangaReaderScreen(
                    index = index,
                    urls = viewModel.chapters,
                    path = if (chapter.contains(Constants.MANGANATO)) Constants.MANGANATO else Constants.KUNMANGA
                ))
            })
        }
    }

}