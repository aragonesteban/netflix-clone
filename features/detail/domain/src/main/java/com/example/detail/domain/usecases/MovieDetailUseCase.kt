package com.example.detail.domain.usecases

import com.example.core.domain.model.MovieDetail
import com.example.core.domain.repositories.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieDetailUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {

    suspend fun getMovieDetailById(movieId: Int): Flow<MovieDetail> =
        moviesRepository.getMovieDetailById(movieId)
}