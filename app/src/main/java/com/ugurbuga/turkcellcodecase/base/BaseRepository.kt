package com.ugurbuga.turkcellcodecase.base

import com.ugurbuga.turkcellcodecase.BuildConfig
import com.ugurbuga.turkcellcodecase.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

abstract class BaseRepository {

    fun <T : Any> onApiCall(call: suspend () -> T): Flow<Resource<T>> =
        flow {
            emit(Resource.Loading)
            emit(Resource.Success(data = call.invoke()))
        }.catch { error ->
            if (BuildConfig.DEBUG) {
                error.printStackTrace()
            }
            emit(Resource.Error(error))
        }.flowOn(Dispatchers.IO)


    fun <T : Any> onRoomCall(call: suspend () -> T): Flow<Resource<T>> =
        flow {
            emit(Resource.Loading)
            emit(Resource.Success(data = call.invoke()))
        }.catch { error ->
            if (BuildConfig.DEBUG) {
                error.printStackTrace()
            }
            emit(Resource.Error(error))
        }.flowOn(Dispatchers.IO)


    fun <T : Any?> onRoomFlowCall(call: Flow<T>): Flow<Resource<T>> =
        flow {
            emit(Resource.Loading)
            call.collect {
                emit(Resource.Success(it))
            }
        }.catch {
            emit(Resource.Error(it))
        }.flowOn(Dispatchers.IO)
}