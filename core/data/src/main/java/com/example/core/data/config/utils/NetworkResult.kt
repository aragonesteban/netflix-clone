package com.example.core.data.config.utils

sealed interface NetworkResult<out T> {
    data class Success<out T>(val data: T) : NetworkResult<T>
    data class Error(val message: String? = String(), val code: Int? = null) :
        NetworkResult<Nothing>
}