package com.example.home.ui.model

sealed class HomeContentType(val name: String) {
    data object Movies : HomeContentType("Movies")
    data object Series : HomeContentType("Series")
}