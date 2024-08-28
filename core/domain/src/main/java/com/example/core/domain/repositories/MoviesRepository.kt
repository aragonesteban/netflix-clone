package com.example.core.domain.repositories

import com.example.core.domain.model.MediaList
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun getMoviesByCategory(category: String): Flow<MediaList>
}