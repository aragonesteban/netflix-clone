package com.example.home.data.repository

import com.example.data.utils.NetflixMapper
import com.example.data.utils.handleNetworkResult
import com.example.data.utils.handleRequest
import com.example.home.data.model.MediaResponse
import com.example.home.data.service.SeriesApiService
import com.example.home.domain.model.MediaList
import com.example.home.domain.repository.SeriesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SeriesRepositoryImpl @Inject constructor(
    private val seriesApi: SeriesApiService,
    private val mapper: NetflixMapper<MediaResponse, MediaList>
): SeriesRepository {

    override suspend fun getSeriesByCategory(category: String): Flow<MediaList> {
        return flow {
            val result = seriesApi.getSeriesByCategory(category).handleRequest { data ->
                mapper.map(data)
            }
            result.handleNetworkResult { data -> emit(data) }
        }.flowOn(Dispatchers.IO)
    }
}