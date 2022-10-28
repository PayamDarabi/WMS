package com.wms.wms.data.model.response

import android.util.Log
import com.google.gson.Gson
import retrofit2.Response

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
class ApiResult<T : Any> {
    var isSuccessful: Boolean
    var data: T? = null
    var errorBody: ExceptionResponse? = null;

    constructor(response: Response<T>) {
        isSuccessful = response.isSuccessful

        try {
            return if (response.isSuccessful) {
                data = response.body()!!
            } else {
                val mJson = response.errorBody()!!.string()
                val gson = Gson()

                errorBody = gson.fromJson(mJson, ExceptionResponse::class.java)
            }
        } catch (Ex: Exception) {
            errorBody = ExceptionResponse(
                isSucceed = false, message = Ex.localizedMessage ?: Ex.message ?: "Failed"
            )
            Ex.localizedMessage?.let {
                Log.e("Error", it)
            }
        }

    }


    constructor(Ex: Exception) {
        isSuccessful = false
        errorBody = ExceptionResponse(
            isSucceed = false, message = Ex.localizedMessage ?: Ex.message ?: "Failed"
        )
        Ex.localizedMessage?.let {
            Log.e("Error", it)
        }
    }
}