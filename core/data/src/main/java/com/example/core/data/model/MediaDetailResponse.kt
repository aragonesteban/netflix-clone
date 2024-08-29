package com.example.core.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MediaDetailResponse(
    @SerialName("id")
    val id: Int?,
    @SerialName("title")
    val title: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("overview")
    val overview: String?,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("release_date")
    val releaseDate: String? = null,
    @SerialName("first_air_date")
    val firstAirDate: String? = null,
    @SerialName("videos")
    val videos: MediaVideoListResponse? = null
) {
    @Serializable
    data class MediaVideoListResponse(
        @SerialName("results")
        val results: List<MediaVideoResponse>?
    )

    @Serializable
    data class MediaVideoResponse(
        @SerialName("key")
        val key: String?,
        @SerialName("official")
        val official: Boolean?
    )
}

