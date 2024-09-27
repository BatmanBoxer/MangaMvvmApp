package com.darwin.mangamvvmapp.features.feature_manga_search.di

import com.darwin.mangamvvmapp.features.feature_manga_search.data.remote.MangaSearchApi
import com.darwin.mangamvvmapp.features.feature_manga_search.data.repository.MangaSearchRepositoryImplementation
import com.darwin.mangamvvmapp.features.feature_manga_search.domain.repository.MangaSearchRepository
import com.darwin.mangamvvmapp.features.feature_manga_search.domain.use_case.UseCaseSearchManga
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MangaSearchModule {
    @Provides
    @Singleton
    fun provideMangaSearchApi(retrofit: Retrofit): MangaSearchApi {
        return retrofit.create(MangaSearchApi::class.java)
    }
    @Provides
    @Singleton
    fun provideMangaRepository(api: MangaSearchApi): MangaSearchRepository {
        return MangaSearchRepositoryImplementation(api)
    }


}
