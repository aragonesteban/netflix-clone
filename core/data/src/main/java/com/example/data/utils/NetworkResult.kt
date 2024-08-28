package com.example.data.utils

sealed interface NetworkResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : NetworkResult<T>
    data class Error(val message: String? = String(), val code: Int? = null) :
        NetworkResult<Nothing>
}