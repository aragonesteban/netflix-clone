package com.example.core.data.di

import com.example.core.data.service.MoviesApiService
import com.example.core.data.service.SeriesApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    fun providesMoviesApiService(retrofit: Retrofit): MoviesApiService =
        retrofit.create(MoviesApiService::class.java)

    @Provides
    fun providesSeriesApiService(retrofit: Retrofit): SeriesApiService =
        retrofit.create(SeriesApiService::class.java)
}