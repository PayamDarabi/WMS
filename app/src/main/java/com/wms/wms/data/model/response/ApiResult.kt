package com.wms.wms.data.model.response

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class ApiResult<out T : Any> {

    data class Success<out T : Any>(val data: T) : ApiResult<T>()
    data class Error(val exception: Exception) : ApiResult<Nothing>()
    data class RequestError(val exception: ExceptionResponse) : ApiResult<Nothing>()
}