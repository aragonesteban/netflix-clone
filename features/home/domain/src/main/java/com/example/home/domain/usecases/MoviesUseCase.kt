package com.example.home.domain.usecases

import com.example.core.domain.model.MediaList
import com.example.core.domain.repositories.MoviesRepository
import com.example.home.domain.utils.POPULAR
import com.example.home.domain.utils.UPCOMING
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {

    suspend fun getPopularMovies(): Flow<MediaList> =
        moviesRepository.getMoviesByCategory(POPULAR)

    suspend fun getUpcomingMovies(): Flow<MediaList> =
        moviesRepository.getMoviesByCategory(UPCOMING)
}