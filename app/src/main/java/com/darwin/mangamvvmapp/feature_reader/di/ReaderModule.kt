package com.darwin.mangamvvmapp.feature_reader.di

import com.darwin.mangamvvmapp.feature_manga_search.data.remote.MangaSearchApi
import com.darwin.mangamvvmapp.feature_reader.data.remote.MangaReaderApi
import com.darwin.mangamvvmapp.feature_reader.data.repository.MangaReaderRespositoryImpl
import com.darwin.mangamvvmapp.feature_reader.domain.repository.MangaReaderRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ReaderModule {

    @Provides
    @Singleton
    fun providesApi(retrofit: Retrofit):MangaReaderApi{
        return retrofit.create(MangaReaderApi::class.java)
    }

    @Provides
    @Singleton
    fun providesRespository(api: MangaReaderApi):MangaReaderRepository{
        return MangaReaderRespositoryImpl(api)
    }


}