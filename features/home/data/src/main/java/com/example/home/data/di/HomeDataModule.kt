package com.example.home.data.di

import com.example.core.data.config.utils.NetflixMapper
import com.example.core.data.model.MediaResponse
import com.example.core.domain.model.MediaList
import com.example.home.data.mappers.MediaMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface HomeDataModule {

    @Binds
    fun bindsMediaMapper(
        mapper: MediaMapper
    ): NetflixMapper<MediaResponse, MediaList>
}