package com.example.home.data.service

import com.example.home.data.model.MediaResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SeriesApiService {

    @GET("tv/{$CATEGORY}")
    suspend fun getSeriesByCategory(
        @Path(CATEGORY) category: String
    ): Response<MediaResponse>

    private companion object {
        const val CATEGORY = "category"
    }
}