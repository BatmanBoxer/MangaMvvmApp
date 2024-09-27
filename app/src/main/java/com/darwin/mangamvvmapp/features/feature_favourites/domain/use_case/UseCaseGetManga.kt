package com.darwin.mangamvvmapp.features.feature_favourites.domain.use_case

import com.darwin.mangamvvmapp.commons.Resource
import com.darwin.mangamvvmapp.features.feature_favourites.domain.model.FavouritesResult
import com.darwin.mangamvvmapp.features.feature_favourites.domain.repository.FavouritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UseCaseGetManga @Inject constructor(private val repository: FavouritesRepository) {
    operator fun invoke(): Flow<Resource<List<FavouritesResult>>> = flow {
        emit(Resource.Loading())
        try {
            val favouritesManga = repository.getFavouritesManga()
            emit(Resource.Success(favouritesManga))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unknown Error"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Unknown Error"))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unknown Error"))
        }
    }
}