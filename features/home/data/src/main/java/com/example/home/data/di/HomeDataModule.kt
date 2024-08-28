package com.example.home.data.di

import com.example.data.utils.NetflixMapper
import com.example.home.data.mappers.MediaMapper
import com.example.home.data.model.MediaResponse
import com.example.home.data.repository.MoviesRepositoryImpl
import com.example.home.data.repository.SeriesRepositoryImpl
import com.example.home.data.service.MoviesApiService
import com.example.home.data.service.SeriesApiService
import com.example.home.domain.model.MediaList
import com.example.home.domain.repository.MoviesRepository
import com.example.home.domain.repository.SeriesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
interface HomeDataModule {

    @Binds
    fun bindsMediaMapper(
        mapper: MediaMapper
    ): NetflixMapper<MediaResponse, MediaList>

    @Binds
    fun bindsMoviesRepository(
        repository: MoviesRepositoryImpl
    ): MoviesRepository

    @Binds
    fun bindsSeriesRepository(
        repository: SeriesRepositoryImpl
    ): SeriesRepository

    companion object {

        @Provides
        fun providesMoviesApiService(retrofit: Retrofit): MoviesApiService =
            retrofit.create(MoviesApiService::class.java)

        @Provides
        fun providesSeriesApiService(retrofit: Retrofit): SeriesApiService =
            retrofit.create(SeriesApiService::class.java)
    }
}