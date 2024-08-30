package com.example.core.data.local.di

import android.app.Application
import androidx.room.Room
import com.example.core.data.local.database.MediaDao
import com.example.core.data.local.database.MediaDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val NAME_DATABASE = "NetflixCloneDatabase"

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesMediaDatabase(app: Application): MediaDatabase {
        return Room.databaseBuilder(
            app,
            MediaDatabase::class.java,
            NAME_DATABASE
        ).build()
    }

    @Provides
    @Singleton
    fun provideMoviesDao(moviesDatabase: MediaDatabase): MediaDao {
        return moviesDatabase.moviesDao()
    }
}