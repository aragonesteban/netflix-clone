package com.example.core.data.remote

import android.content.Context
import com.example.core.data.remote.model.MediaDetailResponse
import com.example.core.data.remote.model.MediaResponse
import com.example.core.data.remote.service.SeriesApiService
import com.example.core.data.utils.NetflixMapper
import com.example.core.domain.model.MediaDetail
import com.example.core.domain.model.MediaList
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SeriesRemoteData @Inject constructor(
    private val seriesApi: SeriesApiService,
    private val mapper: NetflixMapper<MediaResponse, MediaList>,
    private val movieDetailMapper: NetflixMapper<MediaDetailResponse, MediaDetail>,
    context: Context
) : BaseRemoteData(context) {

    suspend fun getSeriesByCategory(
        category: String
    ): Flow<MediaList> = fetchRemoteData(
        apiCall = { seriesApi.getSeriesByCategory(category) },
        mapper = mapper
    )

    suspend fun getSeriesDetailById(
        seriesId: Int
    ): Flow<MediaDetail> = fetchRemoteData(
        apiCall = { seriesApi.getSeriesDetailById(seriesId) },
        mapper = movieDetailMapper
    )

    suspend fun getSimilarSeriesById(
        seriesId: Int
    ): Flow<MediaList> = fetchRemoteData(
        apiCall = { seriesApi.getSimilarSeriesById(seriesId) },
        mapper = mapper
    )
}