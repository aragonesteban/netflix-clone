package com.example.core.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailResponse(
    @SerialName("id")
    val id: Int?,
    @SerialName("title")
    val title: String?,
    @SerialName("overview")
    val overview: String?,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("release_date")
    val releaseDate: String?,
    @SerialName("videos")
    val videos: MovieVideoListResponse?
) {
    @Serializable
    data class MovieVideoListResponse(
        val results: List<MovieVideoResponse>?
    )

    @Serializable
    data class MovieVideoResponse(
        val key: String?,
        val official: Boolean?
    )
}

