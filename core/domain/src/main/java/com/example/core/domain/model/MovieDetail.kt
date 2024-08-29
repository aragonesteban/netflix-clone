package com.example.core.domain.model

data class MovieDetail(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    val year: String,
    val videoYoutubeKey: String,
)