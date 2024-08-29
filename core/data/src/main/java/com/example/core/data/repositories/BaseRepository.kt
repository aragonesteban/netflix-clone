package com.example.core.data.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseRepository {

    fun <T> fetchData(
        localData: () -> T?,
        remoteData: suspend () -> Flow<T>,
        saveRemoteData: suspend (T) -> Unit
    ): Flow<T> = flow {
        val localResult = localData()
        if (localResult != null) {
            emit(localResult)
        } else {
            remoteData().collect { remoteResult ->
                saveRemoteData(remoteResult)
                emit(remoteResult)
            }
        }
    }.flowOn(Dispatchers.IO)
}