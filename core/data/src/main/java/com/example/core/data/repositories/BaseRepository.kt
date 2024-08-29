package com.example.core.data.repositories

import android.content.Context
import com.example.core.data.config.utils.NetflixMapper
import com.example.core.data.config.utils.handleNetworkResult
import com.example.core.data.config.utils.handleRequest
import com.example.core.data.utils.NetworkUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import java.io.IOException

abstract class BaseRepository(private val context: Context) {

    protected fun <T, R> fetchData(
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
    }.flowOn(Dispatchers.IO)
}