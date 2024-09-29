package com.darwin.mangamvvmapp.features.feature_manga_info.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.darwin.mangamvvmapp.commons.Constants
import com.darwin.mangamvvmapp.features.feature_manga_info.presentation.MangaInfoViewModel
import com.darwin.mangamvvmapp.navigation.NavMangaInfoScreen
import com.darwin.mangamvvmapp.navigation.NavMangaReaderScreen
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MangaInfoScreen(
    viewModel: MangaInfoViewModel = hiltViewModel(),
    navController: NavController,
    scrollBehavior: TopAppBarScrollBehavior
) {


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection)
    ) {
        item {
            Row() {
                AsyncImage(
                    model = viewModel.img,
                    contentDescription = viewModel.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth(0.4f)
                        .padding(10.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
                Column(modifier = Modifier.padding(vertical = 30.dp, horizontal = 10.dp)) {
                    Text(
                        text = viewModel.title,
                        fontSize = 30.sp
                    )
                    Text(
                        text = "Unknown Author"
                    )
                    Row {
                        Icon(Icons.Filled.DateRange, contentDescription = "Date Range")
                        Text("Ongoing. ")
                        Text(viewModel.path)
                    }
                    Spacer(Modifier.height(20.dp))
                    Column() {
                        IconButton(onClick = {
                            viewModel.clickFavourite()
                        }) {
                            Icon(
                                if (viewModel.favouriteState) Icons.Filled.Favorite else
                                    Icons.Filled.FavoriteBorder,
                                contentDescription = "Favourite",
                                modifier = Modifier.size(200.dp),
                                tint = if (viewModel.favouriteState) Color(0xFF87CEEB) else Color.LightGray
                            )
                        }
                        Text(
                            text = if (viewModel.favouriteState) "In Library" else "Add To Library"
                        )
                    }

                }
            }
        }
        itemsIndexed(viewModel.chapters) { index, chapter ->
            Column(modifier = Modifier.padding(5.dp)) {
                Text(
                    text = chapter.title,
                    modifier = Modifier.clickable {
                        navController.navigate(
                            NavMangaReaderScreen(
                                index = index,
                                urls = viewModel.chapters.map { it.url },
                                path = if (chapter.url.contains(Constants.MANGANATO)) Constants.MANGANATO else Constants.KUNMANGA
                            )
                        )
                    },
                    fontSize = 20.sp
                )
                Text("13/11/2023")
            }

        }
    }

}