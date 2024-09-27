package com.darwin.mangamvvmapp.features.feature_manga_info.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.darwin.mangamvvmapp.commons.Constants
import com.darwin.mangamvvmapp.commons.Resource
import com.darwin.mangamvvmapp.features.feature_manga_info.domain.use_case.UseCaseGetMangaInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MangaInfoViewModel @Inject constructor(
    private val useCaseGetMangaInfo: UseCaseGetMangaInfo,
    savedStateHandle: SavedStateHandle
):ViewModel() {
    var chapters = mutableStateListOf<String>()
    init {
        val url: String = savedStateHandle["url"] ?: ""
        val path: String = savedStateHandle["path"] ?: ""
        if (url.isNotEmpty() && path.isNotEmpty()) {
            getMangaInfo(url, path)
        }
    }
     fun getMangaInfo(url:String, path:String){
         Log.d("Batman","info2")
        useCaseGetMangaInfo(url = url,path = path).onEach {result->
                when(result){
                    is Resource.Error -> {}
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        chapters.addAll(result.data?.chapters?.map { it.url } ?: emptyList())
                        Log.d("Batman",result.data.toString())
                    }
                }
        }.launchIn(viewModelScope)
    }
}