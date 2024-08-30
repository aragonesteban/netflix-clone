package com.example.core.data.remote.di

import com.example.core.data.remote.movies.MoviesRemoteData
import com.example.core.data.remote.movies.MoviesRemoteDataImpl
import com.example.core.data.remote.series.SeriesRemoteData
import com.example.core.data.remote.series.SeriesRemoteDataImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RemoteDataModule {

    @Binds
    fun bindMoviesRemoteData(
        impl: MoviesRemoteDataImpl
    ): MoviesRemoteData

    @Binds
    fun bindSeriesRemoteData(
        impl: SeriesRemoteDataImpl
    ): SeriesRemoteData
}