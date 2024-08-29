package com.example.core.data.remote

import android.content.Context
import com.example.core.data.remote.config.utils.NetworkUtil
import com.example.core.data.remote.config.utils.handleNetworkResult
import com.example.core.data.remote.config.utils.handleRequest
import com.example.core.data.utils.NetflixMapper
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.io.IOException

abstract class BaseRemoteData(private val context: Context) {

    protected fun <T, R> fetchRemoteData(
        apiCall: suspend () -> Response<T>,
        mapper: NetflixMapper<T, R>
    ) = flow {
        if (NetworkUtil.isNetworkAvailable(context)) {
            val result = apiCall().handleRequest { data ->
                mapper.map(data)
            }
            result.handleNetworkResult { data -> emit(data) }
        } else {
            throw IOException("No internet connection")
        }
    }
}