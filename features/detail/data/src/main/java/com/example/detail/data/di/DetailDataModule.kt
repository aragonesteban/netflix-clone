package com.example.detail.data.di

import com.example.core.data.remote.model.MediaDetailResponse
import com.example.core.data.utils.NetflixMapper
import com.example.core.domain.model.MediaDetail
import com.example.detail.data.mappers.MediaDetailMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface DetailDataModule {

    @Binds
    fun bindsMediaDetailMapper(
        mapper: MediaDetailMapper
    ): NetflixMapper<MediaDetailResponse, MediaDetail>
}