package com.example.core.data.utils

const val DEFAULT_POSTER_URL = "https://www.prokerala.com/movies/assets/img/no-poster-available.jpg"
const val BASE_POSTER_URL = "https://image.tmdb.org/t/p/"

fun String?.mapPosterImage(type: String = "original"): String {
    return when {
        isNullOrEmpty() -> DEFAULT_POSTER_URL
        else -> "$BASE_POSTER_URL$type$this"
    }
}