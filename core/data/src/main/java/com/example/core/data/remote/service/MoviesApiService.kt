package com.example.core.data.remote.service

import com.example.core.data.remote.config.utils.Params
import com.example.core.data.remote.model.MediaDetailResponse
import com.example.core.data.remote.model.MediaResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApiService {

    @GET("movie/{${Params.CATEGORY}}")
    suspend fun getMoviesByCategory(
       @Path(Params.CATEGORY) category: String
    ): Response<MediaResponse>

    @GET("movie/{${Params.MOVIE_ID}}")
    suspend fun getMovieDetailById(
        @Path(Params.MOVIE_ID) movieId: Int,
        @Query(Params.APPEND_TO_RESPONSE) appendToResponse: String = Params.VIDEOS
    ): Response<MediaDetailResponse>

    @GET("movie/{${Params.MOVIE_ID}}/similar")
    suspend fun getSimilarMoviesById(
        @Path(Params.MOVIE_ID) movieId: Int
    ): Response<MediaResponse>
}