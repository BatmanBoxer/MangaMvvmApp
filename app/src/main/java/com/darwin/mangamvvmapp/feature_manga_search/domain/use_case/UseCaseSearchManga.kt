package com.darwin.mangamvvmapp.feature_manga_search.domain.use_case

import com.darwin.mangamvvmapp.commons.Resource
import com.darwin.mangamvvmapp.feature_manga_search.domain.model.MangaSearchReasult
import com.darwin.mangamvvmapp.feature_manga_search.domain.repository.MangaSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class UseCaseSearchManga @Inject constructor(
    private val repository: MangaSearchRepository
) {
    operator fun invoke(path: String, name: String): Flow<Resource<List<MangaSearchReasult>>> =
        flow {
            emit(Resource.Loading())
            try {
                emit(Resource.Loading())
                val manga = repository.getMangas(path, name)
                emit(Resource.Success(manga))
            } catch (e: HttpException) {
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
            } catch (e: IOException) {
                emit(Resource.Error("Check your internet"))
            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage ?: "Unexpected Error"))
            }


        }
}