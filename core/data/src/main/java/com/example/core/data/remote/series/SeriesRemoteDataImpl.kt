package com.example.core.data.remote.series

import android.content.Context
import com.example.core.data.remote.BaseRemoteData
import com.example.core.data.remote.model.MediaDetailResponse
import com.example.core.data.remote.model.MediaResponse
import com.example.core.data.remote.service.SeriesApiService
import com.example.core.data.utils.NetflixMapper
import com.example.core.domain.model.MediaDetail
import com.example.core.domain.model.MediaList
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SeriesRemoteDataImpl @Inject constructor(
    private val seriesApi: SeriesApiService,
    private val mediaMapper: NetflixMapper<MediaResponse, MediaList>,
    private val mediaDetailMapper: NetflixMapper<MediaDetailResponse, MediaDetail>,
    context: Context
) : SeriesRemoteData, BaseRemoteData(context) {

    override suspend fun getSeriesByCategory(
        category: String
    ): Flow<MediaList> = fetchRemoteData(
        apiCall = { seriesApi.getSeriesByCategory(category) },
        mapper = mediaMapper
    )

    override suspend fun getSeriesDetailById(
        seriesId: Int
    ): Flow<MediaDetail> = fetchRemoteData(
        apiCall = { seriesApi.getSeriesDetailById(seriesId) },
        mapper = mediaDetailMapper
    )

    override suspend fun getSimilarSeriesById(
        seriesId: Int
    ): Flow<MediaList> = fetchRemoteData(
        apiCall = { seriesApi.getSimilarSeriesById(seriesId) },
        mapper = mediaMapper
    )
}