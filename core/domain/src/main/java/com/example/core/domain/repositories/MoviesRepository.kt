package com.example.core.domain.repositories

import com.example.core.domain.model.MediaList
import com.example.core.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun getMoviesByCategory(category: String): Flow<MediaList>
    suspend fun getMovieDetailById(movieId: Int): Flow<MovieDetail>
}