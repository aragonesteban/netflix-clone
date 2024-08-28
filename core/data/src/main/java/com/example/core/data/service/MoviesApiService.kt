package com.example.core.data.service

import com.example.core.data.config.utils.Params
import com.example.core.data.model.MediaResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesApiService {

    @GET("movie/{${Params.CATEGORY}}")
    suspend fun getMoviesByCategory(
       @Path(Params.CATEGORY) category: String
    ): Response<MediaResponse>
}