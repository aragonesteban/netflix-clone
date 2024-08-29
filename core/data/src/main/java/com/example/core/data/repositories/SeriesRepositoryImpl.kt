package com.example.core.data.repositories

import com.example.core.data.config.utils.NetflixMapper
import com.example.core.data.config.utils.handleNetworkResult
import com.example.core.data.config.utils.handleRequest
import com.example.core.data.model.MediaDetailResponse
import com.example.core.data.model.MediaResponse
import com.example.core.data.service.SeriesApiService
import com.example.core.domain.model.MediaDetail
import com.example.core.domain.model.MediaList
import com.example.core.domain.repositories.SeriesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SeriesRepositoryImpl @Inject constructor(
    private val seriesApi: SeriesApiService,
    private val mapper: NetflixMapper<MediaResponse, MediaList>,
    private val movieDetailMapper: NetflixMapper<MediaDetailResponse, MediaDetail>
) : SeriesRepository {

    override suspend fun getSeriesByCategory(category: String): Flow<MediaList> {
        return flow {
            val result = seriesApi.getSeriesByCategory(category).handleRequest { data ->
                mapper.map(data)
            }
            result.handleNetworkResult { data -> emit(data) }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getSeriesDetailById(seriesId: Int): Flow<MediaDetail> {
        return flow {
            val result = seriesApi.getSeriesDetailById(seriesId).handleRequest { data ->
                movieDetailMapper.map(data)
            }
            result.handleNetworkResult { data -> emit(data) }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getSimilarSeriesById(seriesId: Int): Flow<MediaList> {
        return flow {
            val result = seriesApi.getSimilarSeriesById(seriesId).handleRequest { data ->
                mapper.map(data)
            }
            result.handleNetworkResult { data -> emit(data) }
        }.flowOn(Dispatchers.IO)
    }
}