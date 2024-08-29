package com.example.core.domain.repositories

import com.example.core.domain.model.MediaDetail
import com.example.core.domain.model.MediaList
import kotlinx.coroutines.flow.Flow

interface SeriesRepository {
    suspend fun getSeriesByCategory(category: String): Flow<MediaList>
    suspend fun getSeriesDetailById(seriesId: Int): Flow<MediaDetail>
    suspend fun getSimilarSeriesById(seriesId: Int): Flow<MediaList>
}