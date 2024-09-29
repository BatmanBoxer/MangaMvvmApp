package com.darwin.mangamvvmapp.features.feature_manga_search.presentation.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.darwin.mangamvvmapp.features.feature_manga_search.presentation.MangaSearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopAppBar(
    viewModel: MangaSearchViewModel,scrollBehavior: TopAppBarScrollBehavior

) {
    var isSearchActive by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(isSearchActive) {
        if (isSearchActive){
            focusRequester.requestFocus()
        }
    }
    TopAppBar(
        title = {
            if (!isSearchActive) Text("Manga App")
            else {
                Box{
                    BasicTextField(
                        value = searchText,
                        onValueChange = { searchText = it },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Search // Set the IME action to Search
                        ),
                        modifier = Modifier
                            .background(Color.Transparent) // Make background transparent
                            .focusRequester(focusRequester) // Focus control (optional)
                            .border(BorderStroke(1.dp, Color.Transparent)) // Optional: Border
                            .padding(8.dp), // Padding inside the text field
                        textStyle = TextStyle(
                            color = MaterialTheme.colorScheme.onBackground, // Text color
                            fontSize = 16.sp // Text size
                        ),singleLine = true,
                        keyboardActions = KeyboardActions(
                            onSearch = {
                                viewModel.search(searchText)
                                searchText = ""
                                isSearchActive = !isSearchActive
                            }
                        )

                        )

                    Divider(
                        color = MaterialTheme.colorScheme.onBackground,
                        thickness = 2.dp,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(top = 40.dp)
                    )
                }

            }


        },
        actions = {
            IconButton(onClick = {
                isSearchActive = !isSearchActive
                viewModel.search(searchText)
                searchText = ""

            }) {
                Icon(Icons.Filled.Search, contentDescription = "Search")
            }
            IconButton(onClick = {


            }) {
                Icon(Icons.Filled.Menu, contentDescription = "Menu")
            }
            IconButton(onClick = {


            }) {
                Icon(Icons.Filled.MoreVert, contentDescription = "Vert")
            }

        },
        navigationIcon = {
            if (isSearchActive) IconButton(onClick = {
                isSearchActive = !isSearchActive
            }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        },
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        )
    )
}