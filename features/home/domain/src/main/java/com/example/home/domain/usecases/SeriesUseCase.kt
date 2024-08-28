package com.example.home.domain.usecases

import com.example.core.domain.model.MediaList
import com.example.core.domain.repositories.SeriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SeriesUseCase @Inject constructor(
    private val seriesRepository: SeriesRepository
) {

    suspend fun getPopularSeries(): Flow<MediaList> =
        seriesRepository.getSeriesByCategory(POPULAR)

    suspend fun getTopRatedSeries(): Flow<MediaList> =
        seriesRepository.getSeriesByCategory(TOP_RATED)

    private companion object {
        const val POPULAR = "popular"
        const val TOP_RATED = "top_rated"
    }
}