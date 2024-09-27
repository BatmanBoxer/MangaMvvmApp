package com.darwin.mangamvvmapp.features.feature_reader.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darwin.mangamvvmapp.commons.Constants
import com.darwin.mangamvvmapp.commons.Resource
import com.darwin.mangamvvmapp.features.feature_reader.domain.use_cases.UseCaseGetMangaPages
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MangaReaderViewModel @Inject constructor(
    private val useCaseGetMangaPages: UseCaseGetMangaPages,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var img = mutableStateListOf<String>()
    init {
        val urls: List<String> = savedStateHandle.get<Array<String>>("urls")?.toList() ?: emptyList()
        val path: String = savedStateHandle["path"] ?: ""
        val index :Int = savedStateHandle["index"] ?: 0
        getManga(url = urls[index],path)
        Log.d("superman",urls.toString())

    }
    fun getManga(url:String,path:String) {
        Log.d("batman","path is $path and url is $url")
            useCaseGetMangaPages(path=path,url=url).onEach { reasult ->
                when (reasult) {
                    is Resource.Error -> {Log.d("batman",reasult.message.toString())}
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        img.addAll(reasult.data?.map { it.img } ?: emptyList())
                        Log.d("batman", reasult.data.toString())
                    }
                }
            }.launchIn(viewModelScope)

    }
}
