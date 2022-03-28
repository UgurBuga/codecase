package com.ugurbuga.codecase.common

import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object Util {

    fun isNetworkError(throwable: Throwable): Boolean {
        return throwable is SocketException ||
                throwable is UnknownHostException ||
                throwable is SocketTimeoutException
    }
}