package com.example.core.data.remote.config.utils

import retrofit2.Response
import java.net.UnknownHostException

inline fun <Api, Data> Response<Api>.handleRequest(mapper: (Api) -> Data): NetworkResult<Data> {
    return try {
        return if (isSuccessful) {
            body()?.let {
                NetworkResult.Success(mapper(it))
            } ?: NetworkResult.Error("Response body is null", code())
        } else {
            NetworkResult.Error(errorBody()?.string() ?: message(), code())
        }
    } catch (exception: UnknownHostException) {
        NetworkResult.Error("Network error: ${exception.message}")
    } catch (exception: Exception) {
        NetworkResult.Error("Unexpected error: ${exception.message}")
    }
}

inline fun <T> NetworkResult<T>.handleNetworkResult(onSuccess: (T) -> Unit) {
    when (this) {
        is NetworkResult.Success -> onSuccess(data)
        is NetworkResult.Error -> throw RuntimeException("There was an error $message $code")
    }
}