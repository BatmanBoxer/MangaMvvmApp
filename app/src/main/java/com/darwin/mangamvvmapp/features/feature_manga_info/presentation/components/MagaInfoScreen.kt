package com.darwin.mangamvvmapp.features.feature_manga_info.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.darwin.mangamvvmapp.commons.Constants
import com.darwin.mangamvvmapp.features.feature_manga_info.presentation.MangaInfoViewModel
import com.darwin.mangamvvmapp.navigation.NavMangaReaderScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MangaInfoScreen(
    viewModel: MangaInfoViewModel = hiltViewModel(),
    navController: NavController,
    scrollBehavior: TopAppBarScrollBehavior
) {
    LaunchedEffect(viewModel.readChapters) {
        viewModel.updateReadState()

    }
    if (!viewModel.isLoading && viewModel.isErrror == "") {
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
                    Column(modifier = Modifier.padding(vertical = 20.dp, horizontal = 10.dp)) {
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
                        Row {

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

                            IconButton(onClick = {
                                viewModel.addAddToDb()
                            }) {
                                Icon(
                                    Icons.Filled.Add,
                                    contentDescription = "Favourite",
                                    tint = Color.Green,
                                    modifier = Modifier.size(200.dp),
                                )
                            }
                            IconButton(onClick = {
                                viewModel.removeFromDb()
                            }) {
                                Icon(
                                    Icons.Filled.Clear,
                                    contentDescription = "Favourite",
                                    tint = Color.Red,
                                    modifier = Modifier.size(200.dp),
                                )
                            }
                        }

                    }
                }
            }
            itemsIndexed(viewModel.chapters) { index, chapter ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(
                                NavMangaReaderScreen(
                                    index = index,
                                    urls = viewModel.chapters.map { it.url },
                                    path = if (chapter.url.contains(Constants.MANGANATO)) Constants.MANGANATO else Constants.KUNMANGA,
                                    mangaInfoUrl = viewModel.url
                                )
                            )
                        }
                        .padding(12.dp),  // Adjusted padding for better spacing
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Chapter title with multi-line support
                    Text(
                        text = chapter.title,
                        modifier = Modifier
                            .weight(1f)  // Allows the Text to take available space
                            .padding(end = 8.dp),  // Padding to the right of the title
                        fontSize = 18.sp,  // Adjusted font size
                        color = if (viewModel.readChapters.contains(chapter.url))
                            MaterialTheme.colorScheme.inverseOnSurface
                        else
                            MaterialTheme.colorScheme.onBackground,
                        maxLines = 2,  // Allowing two lines for the title
                        overflow = TextOverflow.Ellipsis  // Ellipsis for overflow text
                    )

                    // Date Text
                    Text(
                        text = "13/11/2023",
                        fontSize = 16.sp,  // Adjusted font size for date
                        color = if (viewModel.readChapters.contains(chapter.url))
                            MaterialTheme.colorScheme.inverseOnSurface
                        else
                            MaterialTheme.colorScheme.onBackground,
                    )
                }


            }
        }
    } else if (viewModel.isErrror == "" && viewModel.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
    if (viewModel.isErrror != "") {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Check your Internet")
        }
    }


}