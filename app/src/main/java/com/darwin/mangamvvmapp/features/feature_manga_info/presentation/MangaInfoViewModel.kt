package com.darwin.mangamvvmapp.features.feature_manga_info.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darwin.mangamvvmapp.commons.Resource
import com.darwin.mangamvvmapp.features.feature_favourites.domain.model.FavouritesResult
import com.darwin.mangamvvmapp.features.feature_favourites.domain.use_case.UseCaseAddManga
import com.darwin.mangamvvmapp.features.feature_favourites.domain.use_case.UseCaseDeleteManga
import com.darwin.mangamvvmapp.features.feature_favourites.domain.use_case.UseCaseGetMangaByUrl
import com.darwin.mangamvvmapp.features.feature_manga_info.domain.model.ChapterResult
import com.darwin.mangamvvmapp.features.feature_manga_info.domain.use_case.UseCaseGetMangaInfo
import com.darwin.mangamvvmapp.features.feature_manga_info.utils.MangaChapterState
import com.darwin.mangamvvmapp.features.feature_reader.data.local.ReadChaptersEntity
import com.darwin.mangamvvmapp.features.feature_reader.domain.use_cases.UseCaseGetChaptersFromDb
import com.darwin.mangamvvmapp.features.feature_reader.domain.use_cases.UseCaseUpsertChapters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MangaInfoViewModel @Inject constructor(
    private val useCaseGetMangaInfo: UseCaseGetMangaInfo,
    private val useCaseAddManga: UseCaseAddManga,
    private val useCaseDeleteManga: UseCaseDeleteManga,
    private val useCaseGetMangaByUrl: UseCaseGetMangaByUrl,
    private val savedStateHandle: SavedStateHandle,
    private val useCaseGetChaptersFromDb: UseCaseGetChaptersFromDb,
    private val useCaseUpsertChapters:UseCaseUpsertChapters
) : ViewModel() {
    val readChapters = mutableStateListOf<String>()
    var chapters = mutableStateListOf<ChapterResult>()
    var genres = mutableStateListOf<String>()
    var description by mutableStateOf("")
    var img by mutableStateOf("")
    var title by mutableStateOf("")
    var favouriteState by mutableStateOf(false)
    var isLoading by mutableStateOf(true)
    var isErrror by mutableStateOf("")



    val url: String = savedStateHandle["url"] ?: ""
    val path: String = savedStateHandle["path"] ?: ""

    init {
        getMangaInfo()
    }
    fun updateReadState() {
        viewModelScope.launch(Dispatchers.Main) {
            val result = useCaseGetChaptersFromDb(url)
            readChapters.clear()  // Clear existing read chapters
            readChapters.addAll(result.chapters)  // Add new read chapters
        }
    }

    fun addAddToDb(){
        viewModelScope.launch {
           useCaseUpsertChapters(ReadChaptersEntity(
               url,
               chapters = chapters.map { it.url }
           ))
        }
        updateReadState()
    }
    fun removeFromDb(){
        viewModelScope.launch {
            useCaseUpsertChapters(
                ReadChaptersEntity(
                url,
                chapters = listOf("")
            )
            )
        }
        updateReadState()
    }
    private fun getFavouriteState(url: String) {
        useCaseGetMangaByUrl(url).onEach { result ->
            Log.d("koirala", result.data.toString())
            when (result) {
                is Resource.Error -> {
                    favouriteState = false
                }
                is Resource.Loading -> {}
                is Resource.Success -> {
                    if (result.data?.url == url) {
                        favouriteState = true
                    }

                }
            }
        }.launchIn(viewModelScope)
    }

    fun clickFavourite() {
        if (!favouriteState) {
            addMangaToFavourite(url = url)
        } else {
            removeMangaFromFavourite(url = url)
        }
    }

    private fun addMangaToFavourite(url: String) {
        viewModelScope.launch {
            useCaseAddManga(
                FavouritesResult(
                    imgUrl = img,
                    name = title,
                    url = url

                )
            )
            getFavouriteState(url)
        }

    }

    private fun removeMangaFromFavourite(url: String) {
        viewModelScope.launch {
            useCaseDeleteManga(url = url)
            getFavouriteState(url)
        }
    }

    fun getMangaInfo() {

        Log.d("Batman", "info2")
        useCaseGetMangaInfo(url = url, path = path).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    isErrror = result.message.toString()
                }
                is Resource.Loading -> {
                    isLoading = true
                }
                is Resource.Success -> {

                    chapters.addAll(result.data?.chapters ?: emptyList())
                    genres.addAll(result.data?.genres ?: emptyList())
                    img = result.data?.img ?: ""
                    title = result.data?.title ?: ""
                    description = result.data?.description ?: ""
                    isLoading = false
                }
            }
        }.launchIn(viewModelScope)
        getFavouriteState(url)
    }
}