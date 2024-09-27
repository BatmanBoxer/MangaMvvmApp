package com.darwin.mangamvvmapp.features.feature_manga_info.di

import com.darwin.mangamvvmapp.features.feature_manga_info.data.remote.GetMangaInfoApi
import com.darwin.mangamvvmapp.features.feature_manga_info.data.repository.GetMangaInfoRepositoryImpl
import com.darwin.mangamvvmapp.features.feature_manga_info.domain.repository.GetMangaInfoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MangaInfoModule {

    @Provides
    @Singleton
    fun providesApi(retrofit: Retrofit): GetMangaInfoApi {
        return retrofit.create(GetMangaInfoApi::class.java)
    }

    @Provides
    @Singleton
    fun providesRepository(api: GetMangaInfoApi): GetMangaInfoRepository {
        return GetMangaInfoRepositoryImpl(api)
    }


}