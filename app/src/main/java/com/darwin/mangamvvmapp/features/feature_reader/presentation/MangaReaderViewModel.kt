package com.darwin.mangamvvmapp.features.feature_reader.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darwin.mangamvvmapp.commons.Constants
import com.darwin.mangamvvmapp.commons.Resource
import com.darwin.mangamvvmapp.features.feature_reader.data.local.ReadChaptersEntity
import com.darwin.mangamvvmapp.features.feature_reader.domain.use_cases.UseCaseApplyProxy
import com.darwin.mangamvvmapp.features.feature_reader.domain.use_cases.UseCaseGetChaptersFromDb
import com.darwin.mangamvvmapp.features.feature_reader.domain.use_cases.UseCaseGetMangaPages
import com.darwin.mangamvvmapp.features.feature_reader.domain.use_cases.UseCaseUpsertChapters
import com.darwin.mangamvvmapp.features.feature_reader.utils.chapternameandurl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MangaReaderViewModel @Inject constructor(
    private val useCaseApplyProxy: UseCaseApplyProxy,
    private val useCaseGetMangaPages: UseCaseGetMangaPages,
    private val savedStateHandle: SavedStateHandle,
    private val useCaseUpsertChapters: UseCaseUpsertChapters,
    private val useCaseGetChaptersFromDb: UseCaseGetChaptersFromDb
) : ViewModel() {
    var img = mutableStateListOf<chapternameandurl>()
    val urls: List<String> = savedStateHandle.get<Array<String>>("urls")?.toList() ?: emptyList()
    val path: String = savedStateHandle["path"] ?: ""
    val index: Int = savedStateHandle["index"] ?: 0
    val mainurl:String = savedStateHandle["mangaInfoUrl"] ?: ""
    var localIndex by mutableStateOf(index)

    init {
        if (index==0){
            viewModelScope.launch {
                useCaseUpsertChapters(readChaptersEntity = ReadChaptersEntity(
                    url = mainurl,
                    chapters = listOf(urls[0])
                ))
            }
            upsertChapters(urls[0])

        }
        getManga(url = urls[index], path)
        Log.d("superman", index.toString())

    }

    fun addManga() {
        if (localIndex > 0) {
            getManga(url = urls[localIndex - 1], path)
            localIndex = localIndex - 1

        upsertChapters( urls[localIndex+1])
        }

    }

    private fun getManga(url: String, path: String) {
        Log.d("batman", "path is $path and url is $url")
        useCaseGetMangaPages(path = path, url = url).onEach { reasult ->
            when (reasult) {
                is Resource.Error -> {
                    Log.d("batman", reasult.message.toString())
                }

                is Resource.Loading -> {}
                is Resource.Success -> {
                    reasult.data?.get(0)?.img?.let {
                        var name = urls[localIndex].split("/")[4]
                        if (path.contains(Constants.KUNMANGA)){
                            name = "Chapter-${(urls.size-localIndex).toString()}"
                        }
                        Log.d("iambatman",it)
                        chapternameandurl(name,reasult.data.map { data ->
                            useCaseApplyProxy(data.img)
                        })
                    }?.let { img.add(it) }
                    Log.d("batman", reasult.data.toString())
                }
            }
        }.launchIn(viewModelScope)

    }
    private fun upsertChapters(chapters:String){
        viewModelScope.launch {
            val dbChapters = getChaptersFromDb()
            useCaseUpsertChapters(readChaptersEntity = ReadChaptersEntity(
                url = mainurl,
                chapters = (dbChapters + chapters).toSet().toList()

            ))
        }
    }
    private suspend fun getChaptersFromDb(): List<String> {
        val dbChapters = useCaseGetChaptersFromDb(mainurl)
        Log.d("chaptersfromdb", dbChapters.toString())
        return dbChapters.chapters
    }
}
