package com.darwin.mangamvvmapp.features.feature_favourites.di

import android.app.Application
import androidx.room.Room
import com.darwin.mangamvvmapp.features.feature_favourites.db.favourites.FavouritesDao
import com.darwin.mangamvvmapp.features.feature_favourites.db.favourites.FavouritesDatabase
import com.darwin.mangamvvmapp.features.feature_favourites.data.repository.FavouritesRepositoryImpl
import com.darwin.mangamvvmapp.features.feature_favourites.domain.repository.FavouritesRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavouritesModule {


    @Provides
    @Singleton
    fun providesDao(app:Application): FavouritesDatabase {
        return Room.databaseBuilder(
            app,
            FavouritesDatabase::class.java,
            "world_db"
        )
            .fallbackToDestructiveMigration()
            .build()

    }
    @Provides
    @Singleton
    fun providesRepository(database: FavouritesDatabase):FavouritesRepository{
        return FavouritesRepositoryImpl(database.dao)
    }
}