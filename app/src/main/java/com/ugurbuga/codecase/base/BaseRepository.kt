package com.ugurbuga.codecase.base

import com.ugurbuga.codecase.BuildConfig
import com.ugurbuga.codecase.common.Resource
import com.ugurbuga.codecase.common.Util
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseRepository {

    fun <T : Any> onApiCall(call: suspend () -> T): Flow<Resource<T>> = flow {
        emit(Resource.Loading)
        emit(Resource.Success(data = call.invoke()))
    }.catch { error ->
        if (BuildConfig.DEBUG) {
            error.printStackTrace()
        }
        emit(Resource.Error(error))
    }.flowOn(Dispatchers.IO)

    fun <T : Any> onOfflineSupportApiCall(
        call: suspend () -> T,
        onDataResult: suspend (T) -> Unit,
        offlineData: suspend () -> T,
    ): Flow<Resource<T>> = flow {
        emit(Resource.Loading)
        val response = call.invoke()
        emit(Resource.Success(data = response))
        onDataResult.invoke(response)
    }.catch { error ->
        if (BuildConfig.DEBUG) {
            error.printStackTrace()
        }
        if (Util.isNetworkError(error)) {
            emit(Resource.Success(data = offlineData.invoke()))
        } else {
            emit(Resource.Error(error))
        }

    }.flowOn(Dispatchers.IO)
}