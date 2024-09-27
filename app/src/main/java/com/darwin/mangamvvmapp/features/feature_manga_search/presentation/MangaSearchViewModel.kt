package com.darwin.mangamvvmapp.features.feature_manga_search.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darwin.mangamvvmapp.commons.Constants
import com.darwin.mangamvvmapp.commons.Resource
import com.darwin.mangamvvmapp.features.feature_manga_search.domain.model.MangaSearchReasult
import com.darwin.mangamvvmapp.features.feature_manga_search.domain.use_case.UseCaseSearchManga

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MangaSearchViewModel @Inject constructor(
    private val useCaseSearchManga: UseCaseSearchManga
):ViewModel() {
    private val _state = mutableStateOf<MangaSearchScreenState>(MangaSearchScreenState())
    val state: State<MangaSearchScreenState?> = _state
    init {
        search()
    }
    fun search(){
        useCaseSearchManga(Constants.MANGANATO,"solo").onEach { result->
            when(result){
                is Resource.Error -> {}
                is Resource.Loading -> {}
                is Resource.Success -> {
                    _state.value = MangaSearchScreenState(mangaSearchResult = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)

    }
}