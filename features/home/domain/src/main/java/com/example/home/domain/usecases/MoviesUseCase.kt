package com.example.home.domain.usecases

import com.example.home.domain.model.MediaList
import com.example.home.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {

    suspend fun getPopularMovies(): Flow<MediaList> =
        moviesRepository.getMoviesByCategory(POPULAR)

    suspend fun getUpcomingMovies(): Flow<MediaList> =
        moviesRepository.getMoviesByCategory(UPCOMING)

    private companion object {
        const val POPULAR = "popular"
        const val UPCOMING = "upcoming"
    }
}