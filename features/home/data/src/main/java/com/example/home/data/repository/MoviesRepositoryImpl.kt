package com.example.home.data.repository

import com.example.data.utils.NetflixMapper
import com.example.data.utils.handleNetworkResult
import com.example.data.utils.handleRequest
import com.example.home.data.mappers.MediaMapper
import com.example.home.data.model.MediaResponse
import com.example.home.data.service.MoviesApiService
import com.example.home.domain.model.MediaList
import com.example.home.domain.repository.MoviesRepository
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