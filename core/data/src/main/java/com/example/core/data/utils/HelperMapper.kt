package com.example.core.data.utils

fun String?.mapPosterImage(type: String): String {
    return if (this.isNullOrEmpty()) {
        "https://www.prokerala.com/movies/assets/img/no-poster-available.jpg"
    } else {
        "https://image.tmdb.org/t/p/$type$this"
    }
}