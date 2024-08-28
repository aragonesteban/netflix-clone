package com.example.core.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MediaResponse(
    val results: List<MediaItem>?
) {
    @Serializable
    data class MediaItem(
        @SerialName("id")
        val id: Int?,
        @SerialName("poster_path")
        val posterPath: String?,
        @SerialName("title")
        val title: String? = null,
        @SerialName("name")
        val name: String? = null
    )
}