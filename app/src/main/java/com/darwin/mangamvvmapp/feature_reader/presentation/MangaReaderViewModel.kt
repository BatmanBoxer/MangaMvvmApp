package com.darwin.mangamvvmapp.feature_reader.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darwin.mangamvvmapp.commons.Constants
import com.darwin.mangamvvmapp.commons.Resource
import com.darwin.mangamvvmapp.feature_reader.domain.use_cases.UseCaseGetMangaPages
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MangaReaderViewModel @Inject constructor(
    private val useCaseGetMangaPages: UseCaseGetMangaPages
) : ViewModel() {
    fun getManga(url:String) {
        //https://chapmanganato.to/manga-ng952689/chapter-700.5
            useCaseGetMangaPages(Constants.MANGANATO,url).onEach { reasult ->
                when (reasult) {
                    is Resource.Error -> {Log.d("batman",reasult.message.toString())}
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        Log.d("batman", reasult.data.toString())
                    }
                }
            }.launchIn(viewModelScope)

    }
}
