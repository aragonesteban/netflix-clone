package com.example.core.data.repositories.di

import com.example.core.data.repositories.MoviesRepositoryImpl
import com.example.core.data.repositories.SeriesRepositoryImpl
import com.example.core.domain.repositories.MoviesRepository
import com.example.core.domain.repositories.SeriesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @Binds
    fun bindsMoviesRepository(
        repository: MoviesRepositoryImpl
    ): MoviesRepository

    @Binds
    fun bindsSeriesRepository(
        repository: SeriesRepositoryImpl
    ): SeriesRepository
}