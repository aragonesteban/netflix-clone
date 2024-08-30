package com.example.core.data.remote.movies

import com.example.core.domain.model.MediaDetail
import com.example.core.domain.model.MediaList
import kotlinx.coroutines.flow.Flow

interface MoviesRemoteData {
    suspend fun getMoviesByCategory(category: String): Flow<MediaList>
    suspend fun getMovieDetailById(movieId: Int): Flow<MediaDetail>
    suspend fun getSimilarMoviesById(movieId: Int): Flow<MediaList>
}