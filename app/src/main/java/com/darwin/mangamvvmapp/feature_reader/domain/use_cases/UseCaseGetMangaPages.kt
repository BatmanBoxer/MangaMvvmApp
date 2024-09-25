package com.darwin.mangamvvmapp.feature_reader.domain.use_cases

import com.darwin.mangamvvmapp.commons.Resource
import com.darwin.mangamvvmapp.feature_reader.domain.model.MangaReaderReasult
import com.darwin.mangamvvmapp.feature_reader.domain.repository.MangaReaderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class UseCaseGetMangaPages @Inject constructor(
   private val repository: MangaReaderRepository
) {
    operator fun invoke(path: String,url:String):Flow<Resource<List<MangaReaderReasult>>> = flow{
        emit(Resource.Loading())
        try {
            val images = repository.getMangaPages(path,url)
            emit(Resource.Success(images))
        }catch (e:HttpException){
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error"))
        }catch (e:IOException){
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error"))
        }catch (e:Exception){
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error"))
        }
    }
}