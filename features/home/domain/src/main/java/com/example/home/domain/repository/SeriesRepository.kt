package com.example.home.domain.repository

import com.example.home.domain.model.MediaList
import kotlinx.coroutines.flow.Flow

interface SeriesRepository {
    suspend fun getSeriesByCategory(category: String): Flow<MediaList>
}