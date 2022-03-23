package com.ugurbuga.turkcellcodecase.base

import com.ugurbuga.turkcellcodecase.Resource
import kotlinx.coroutines.flow.Flow

abstract class BaseUseCase<Request, Response> {
    operator fun invoke(params: Request): Flow<Resource<Response>> = execute(params)

    protected abstract fun execute(params: Request): Flow<Resource<Response>>
}
