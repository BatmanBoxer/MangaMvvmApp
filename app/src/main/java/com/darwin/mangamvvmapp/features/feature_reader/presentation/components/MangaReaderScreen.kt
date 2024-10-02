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
import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    val coroutineScope = rememberCoroutineScope()

    // Flag to avoid triggering pagination multiple times
    var isPaginating by remember { mutableStateOf(false) }

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo }
            .debounce(500)  // Adjust debounce time as needed
            .collectLatest { layoutInfo ->
                val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()?.index
                val totalItems = layoutInfo.totalItemsCount

                // Check if the user is near the end of the list and if pagination is not already triggered
                if (lastVisibleItem != null && lastVisibleItem == totalItems - 1 && !isPaginating) {
                    isPaginating = true // Set the flag to true when pagination starts

                    Log.d("Pagination", "Reached the end, loading more items")
                    viewModel.addManga()
                    isPaginating = false
//                    coroutineScope.launch {
//                        kotlinx.coroutines.delay(1000) // Simulating a network call or loading delay
//                        isPaginating = false
//                    }
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
            Box(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(chapter.name, fontSize = 30.sp)
            }
            chapter.img.map {
                AsyncImage(
                    modifier = Modifier.fillMaxWidth(),
                    model = it,
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    placeholder = painterResource(R.drawable.placeholder_reader),
                )
            }
        }
    }
}
