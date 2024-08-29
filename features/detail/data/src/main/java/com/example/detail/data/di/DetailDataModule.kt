package com.example.detail.data.di

import com.example.core.data.config.utils.NetflixMapper
import com.example.core.data.model.MovieDetailResponse
import com.example.core.domain.model.MovieDetail
import com.example.detail.data.mappers.MovieDetailMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface DetailDataModule {

    @Binds
    fun bindsDetailMapper(
        mapper: MovieDetailMapper
    ): NetflixMapper<MovieDetailResponse, MovieDetail>
}