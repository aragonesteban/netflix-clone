package com.example.core.data.remote.movies

import android.content.Context
import com.example.core.data.remote.BaseRemoteData
import com.example.core.data.remote.model.MediaDetailResponse
import com.example.core.data.remote.model.MediaResponse
import com.example.core.data.remote.service.MoviesApiService
import com.example.core.data.utils.NetflixMapper
import com.example.core.domain.model.MediaDetail
import com.example.core.domain.model.MediaList
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRemoteDataImpl @Inject constructor(
    private val moviesApi: MoviesApiService,
    private val mediaMapper: NetflixMapper<MediaResponse, MediaList>,
    private val mediaDetailMapper: NetflixMapper<MediaDetailResponse, MediaDetail>,
    context: Context
): MoviesRemoteData, BaseRemoteData(context) {

    override suspend fun getMoviesByCategory(
        category: String
    ): Flow<MediaList> = fetchRemoteData(
        apiCall = { moviesApi.getMoviesByCategory(category) },
        mapper = mediaMapper
    )

    override suspend fun getMovieDetailById(
        movieId: Int
    ): Flow<MediaDetail> = fetchRemoteData(
        apiCall = { moviesApi.getMovieDetailById(movieId) },
        mapper = mediaDetailMapper
    )

    override suspend fun getSimilarMoviesById(
        movieId: Int
    ): Flow<MediaList> = fetchRemoteData(
        apiCall = { moviesApi.getSimilarMoviesById(movieId) },
        mapper = mediaMapper
    )
}