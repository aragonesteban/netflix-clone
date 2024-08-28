package com.example.home.domain.repository

import com.example.home.domain.model.MediaList
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun getMoviesByCategory(category: String): Flow<MediaList>
}