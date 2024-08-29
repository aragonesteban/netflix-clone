package com.example.detail.domain.usecases

import com.example.core.domain.model.MediaDetail
import com.example.core.domain.model.MediaList
import com.example.core.domain.repositories.MoviesRepository
import com.example.core.domain.repositories.SeriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MediaDetailUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val seriesRepository: SeriesRepository
) {

    suspend fun getMovieDetailById(movieId: Int): Flow<MediaDetail> =
        moviesRepository.getMovieDetailById(movieId)

    suspend fun getSimilarMoviesById(movieId: Int): Flow<MediaList> =
        moviesRepository.getSimilarMoviesById(movieId)

    suspend fun getSeriesDetailById(seriesId: Int): Flow<MediaDetail> =
        seriesRepository.getSeriesDetailById(seriesId)

    suspend fun getSimilarSeriesById(seriesId: Int): Flow<MediaList> =
        seriesRepository.getSimilarSeriesById(seriesId)
}