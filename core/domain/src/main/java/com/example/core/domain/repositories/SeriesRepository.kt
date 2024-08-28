package com.example.core.domain.repositories

import com.example.core.domain.model.MediaList
import kotlinx.coroutines.flow.Flow

interface SeriesRepository {
    suspend fun getSeriesByCategory(category: String): Flow<MediaList>
}