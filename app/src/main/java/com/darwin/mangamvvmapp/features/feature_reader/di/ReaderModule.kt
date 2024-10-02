package com.darwin.mangamvvmapp.features.feature_reader.di

import android.app.Application
import androidx.room.Room
import com.darwin.mangamvvmapp.features.feature_favourites.data.repository.FavouritesRepositoryImpl
import com.darwin.mangamvvmapp.features.feature_favourites.db.favourites.FavouritesDatabase
import com.darwin.mangamvvmapp.features.feature_favourites.domain.repository.FavouritesRepository

import com.darwin.mangamvvmapp.features.feature_reader.data.local.ReadChapterDatabase
import com.darwin.mangamvvmapp.features.feature_reader.data.remote.MangaReaderApi
import com.darwin.mangamvvmapp.features.feature_reader.data.repository.MangaReaderRespositoryImpl
import com.darwin.mangamvvmapp.features.feature_reader.domain.repository.MangaReaderRepository
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
    fun providesDao(app: Application): ReadChapterDatabase{
        return Room.databaseBuilder(
            app,
            ReadChapterDatabase::class.java,
            "read_chapters"
        )
            .fallbackToDestructiveMigration()
            .build()

    }
    @Provides
    @Singleton
    fun providesApi(retrofit: Retrofit): MangaReaderApi {
        return retrofit.create(MangaReaderApi::class.java)
    }

    @Provides
    @Singleton
    fun providesRespository(api: MangaReaderApi,database: ReadChapterDatabase): MangaReaderRepository {
        return MangaReaderRespositoryImpl(api,database.dao)
    }


}