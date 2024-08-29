package com.example.core.data.repositories

import android.content.Context
import com.example.core.data.config.utils.NetflixMapper
import com.example.core.data.model.MediaDetailResponse
import com.example.core.data.model.MediaResponse
import com.example.core.data.service.SeriesApiService
import com.example.core.domain.model.MediaDetail
import com.example.core.domain.model.MediaList
import com.example.core.domain.repositories.SeriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SeriesRepositoryImpl @Inject constructor(
    private val seriesApi: SeriesApiService,
    private val mapper: NetflixMapper<MediaResponse, MediaList>,
    private val movieDetailMapper: NetflixMapper<MediaDetailResponse, MediaDetail>,
    context: Context
) : SeriesRepository, BaseRepository(context) {

    override suspend fun getSeriesByCategory(
        category: String
    ): Flow<MediaList> = fetchData(
        apiCall = { seriesApi.getSeriesByCategory(category) },
        mapper = mapper
    )

    override suspend fun getSeriesDetailById(
        seriesId: Int
    ): Flow<MediaDetail> = fetchData(
        apiCall = { seriesApi.getSeriesDetailById(seriesId) },
        mapper = movieDetailMapper
    )

    override suspend fun getSimilarSeriesById(
        seriesId: Int
    ): Flow<MediaList> = fetchData(
        apiCall = { seriesApi.getSimilarSeriesById(seriesId) },
        mapper = mapper
    )
}