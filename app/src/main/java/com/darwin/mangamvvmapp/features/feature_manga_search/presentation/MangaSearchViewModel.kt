package com.darwin.mangamvvmapp.features.feature_manga_search.presentation

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darwin.mangamvvmapp.commons.Constants
import com.darwin.mangamvvmapp.commons.Resource
import com.darwin.mangamvvmapp.features.feature_manga_search.domain.use_case.UseCaseSearchManga
import com.darwin.mangamvvmapp.service.PreferencesManager

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MangaSearchViewModel @Inject constructor(
    private val useCaseSearchManga: UseCaseSearchManga,
    private val context :Application
) : ViewModel() {
    private val _state = mutableStateOf<MangaSearchScreenState>(MangaSearchScreenState())
    val state: State<MangaSearchScreenState?> = _state
    var path by mutableStateOf("")
    val preferencesManager = PreferencesManager(context)
    init {
        search("solo")
    }

    fun search(name: String) {
        path = preferencesManager.getSelectedOption() ?: Constants.MANGANATO
        if (name.isNotBlank()){
            useCaseSearchManga(path, name).onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.value = MangaSearchScreenState(error = result.message!!)
                    }

                    is Resource.Loading -> {
                        _state.value = MangaSearchScreenState(isLoading = true)
                    }

                    is Resource.Success -> {
                        _state.value = MangaSearchScreenState(
                            mangaSearchResult = result.data ?: emptyList(),
                            isLoading = false
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }

    }

}