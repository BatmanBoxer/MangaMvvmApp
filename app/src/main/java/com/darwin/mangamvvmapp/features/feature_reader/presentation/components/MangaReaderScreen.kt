package com.darwin.mangamvvmapp.features.feature_reader.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.darwin.mangamvvmapp.commons.Constants
import com.darwin.mangamvvmapp.features.feature_reader.presentation.MangaReaderViewModel
import com.darwin.mangamvvmapp.navigation.NavMangaReaderScreen

@Composable
fun MangaReaderScreen(
    args: NavMangaReaderScreen,
    viewModel: MangaReaderViewModel = hiltViewModel<MangaReaderViewModel>()
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(viewModel.img){chapter ->
            Text(chapter)
        }
    }

}