package com.darwin.mangamvvmapp.feature_reader.presentation.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.darwin.mangamvvmapp.feature_reader.presentation.MangaReaderViewModel

@Composable
fun MangaReaderScreen(
    url:String,
     viewModel: MangaReaderViewModel = hiltViewModel<MangaReaderViewModel>()
){
    Button(onClick = {
        viewModel.getManga(url)
    }) {
        Text("Reader Screen")
    }
}