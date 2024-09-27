package com.darwin.mangamvvmapp.features.feature_manga_info.domain.use_case

import com.darwin.mangamvvmapp.commons.Resource
import com.darwin.mangamvvmapp.features.feature_manga_info.domain.model.MangaInfoResult
import com.darwin.mangamvvmapp.features.feature_manga_info.domain.repository.GetMangaInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UseCaseGetMangaInfo @Inject constructor(
    private val repository: GetMangaInfoRepository
) {
    operator fun invoke(url: String, path: String): Flow<Resource<MangaInfoResult>> = flow {
        try {
            val images = repository.getMangaInfo(path, url)
            emit(Resource.Success(images))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error"))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error"))
        }
    }
}