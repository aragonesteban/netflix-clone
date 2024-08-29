package com.example.core.data.remote.service

import com.example.core.data.remote.config.utils.Params
import com.example.core.data.remote.model.MediaDetailResponse
import com.example.core.data.remote.model.MediaResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SeriesApiService {

    @GET("tv/{${Params.CATEGORY}}")
    suspend fun getSeriesByCategory(
        @Path(Params.CATEGORY) category: String
    ): Response<MediaResponse>

    @GET("tv/{${Params.SERIES_ID}}")
    suspend fun getSeriesDetailById(
        @Path(Params.SERIES_ID) seriesId: Int,
        @Query(Params.APPEND_TO_RESPONSE) appendToResponse: String = Params.VIDEOS
    ): Response<MediaDetailResponse>

    @GET("tv/{${Params.SERIES_ID}}/similar")
    suspend fun getSimilarSeriesById(
        @Path(Params.SERIES_ID) seriesId: Int
    ): Response<MediaResponse>
}