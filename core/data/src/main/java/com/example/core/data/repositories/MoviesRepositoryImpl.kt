package com.example.core.data.repositories

import com.example.core.data.config.utils.NetflixMapper
import com.example.core.data.config.utils.handleNetworkResult
import com.example.core.data.config.utils.handleRequest
import com.example.core.data.model.MediaResponse
import com.example.core.data.service.MoviesApiService
import com.example.core.domain.model.MediaList
import com.example.core.domain.repositories.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val moviesApi: MoviesApiService,
    private val mapper: NetflixMapper<MediaResponse, MediaList>
) : MoviesRepository {

    override suspend fun getMoviesByCategory(category: String): Flow<MediaList> {
        return flow {
            val result = moviesApi.getMoviesByCategory(category).handleRequest { data ->
                mapper.map(data)
            }
            result.handleNetworkResult { data -> emit(data) }
        }.flowOn(Dispatchers.IO)
    }
}