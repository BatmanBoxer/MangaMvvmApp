package com.darwin.mangamvvmapp.features.feature_reader.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import kotlinx.coroutines.launch
import androidx.compose.ui.res.painterResource
import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.darwin.mangamvvmapp.R
import com.darwin.mangamvvmapp.features.feature_reader.presentation.MangaReaderViewModel
import com.darwin.mangamvvmapp.navigation.NavMangaReaderScreen
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce

@OptIn(FlowPreview::class)
@Composable
fun MangaReaderScreen(
    args: NavMangaReaderScreen,
    viewModel: MangaReaderViewModel = hiltViewModel<MangaReaderViewModel>()
) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .crossfade(true)
        .build()

    val listState = rememberLazyListState()

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo }
            .debounce(1000)
            .collectLatest { visibleItems ->
                val lastVisibleItem = visibleItems.lastOrNull()
                val totalItems = listState.layoutInfo.totalItemsCount
                if (lastVisibleItem != null && lastVisibleItem.index >= totalItems - 3) {
                    Log.d("bottom", "Reached near the bottom")
                    viewModel.addManga()
                }
            }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = listState
    ) {
        itemsIndexed(viewModel.img) { index, chapter ->
            if (index <= 5) {
                val request = ImageRequest.Builder(context)
                    .data(chapter)
                    .allowHardware(false)
                    .build()

                imageLoader.enqueue(request)
            }

            Log.d("images", chapter)

            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = chapter,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                placeholder = painterResource(R.drawable.placeholder_reader),
            )
        }
    }
}
