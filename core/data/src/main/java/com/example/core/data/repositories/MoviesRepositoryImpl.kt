package com.example.core.data.repositories

import android.content.Context
import com.example.core.data.config.utils.NetflixMapper
import com.example.core.data.model.MediaDetailResponse
import com.example.core.data.model.MediaResponse
import com.example.core.data.service.MoviesApiService
import com.example.core.domain.model.MediaDetail
import com.example.core.domain.model.MediaList
import com.example.core.domain.repositories.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val moviesApi: MoviesApiService,
    private val mediaMapper: NetflixMapper<MediaResponse, MediaList>,
    private val mediaDetailMapper: NetflixMapper<MediaDetailResponse, MediaDetail>,
    context: Context
) : MoviesRepository, BaseRepository(context) {

    override suspend fun getMoviesByCategory(
        category: String
    ): Flow<MediaList> = fetchData(
        apiCall = { moviesApi.getMoviesByCategory(category) },
        mapper = mediaMapper
    )

    override suspend fun getMovieDetailById(
        movieId: Int
    ): Flow<MediaDetail> = fetchData(
        apiCall = { moviesApi.getMovieDetailById(movieId) },
        mapper = mediaDetailMapper
    )

    override suspend fun getSimilarMoviesById(
        movieId: Int
    ): Flow<MediaList> = fetchData(
        apiCall = { moviesApi.getSimilarMoviesById(movieId) },
        mapper = mediaMapper
    )
}