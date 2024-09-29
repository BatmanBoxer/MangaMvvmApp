package com.darwin.mangamvvmapp.features.feature_favourites.presentaion

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darwin.mangamvvmapp.commons.Resource
import com.darwin.mangamvvmapp.features.feature_favourites.db.favourites.FavouritesEntity
import com.darwin.mangamvvmapp.features.feature_favourites.domain.model.FavouritesResult
import com.darwin.mangamvvmapp.features.feature_favourites.domain.repository.FavouritesRepository
import com.darwin.mangamvvmapp.features.feature_favourites.domain.use_case.UseCaseAddManga
import com.darwin.mangamvvmapp.features.feature_favourites.domain.use_case.UseCaseGetManga
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import okhttp3.internal.notify
import javax.inject.Inject

@HiltViewModel
class MangaFavouritesViewModel @Inject constructor(
    private val useCaseGetManga: UseCaseGetManga,
):ViewModel() {
    var favMangas = mutableStateListOf<FavouritesResult>()
    init {
        getManga()
    }
    private fun getManga(){
        useCaseGetManga().onEach { result->
            when(result){
                is Resource.Error -> {}
                is Resource.Loading -> {}
                is Resource.Success -> {
                    val favMangasfromdb = result.data
                    favMangas.addAll(favMangasfromdb ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)

    }
}