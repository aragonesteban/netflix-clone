package com.example.core.data.repositories

import com.example.core.data.config.utils.NetflixMapper
import com.example.core.data.config.utils.handleNetworkResult
import com.example.core.data.config.utils.handleRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

abstract class BaseRepository {

    protected fun <T, R> fetchData(
        apiCall: Response<T>,
        mapper: NetflixMapper<T, R>
    ) = flow {
        val result = apiCall.handleRequest { data ->
            mapper.map(data)
        }
        result.handleNetworkResult { data -> emit(data) }
    }.flowOn(Dispatchers.IO)
}