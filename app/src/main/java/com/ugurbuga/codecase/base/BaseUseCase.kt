package com.ugurbuga.codecase.base

import com.ugurbuga.codecase.Resource
import kotlinx.coroutines.flow.Flow

abstract class BaseUseCase<Request, Response> {
    operator fun invoke(params: Request): Flow<Resource<Response>> = execute(params)

    protected abstract fun execute(params: Request): Flow<Resource<Response>>
}
