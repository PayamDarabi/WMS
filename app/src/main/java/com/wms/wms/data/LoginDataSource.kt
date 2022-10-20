package com.wms.wms.data

import android.util.Log
import com.wms.wms.data.api.IApi
import com.wms.wms.data.api.RetrofitClient
import com.wms.wms.data.model.response.ExceptionResponse
import com.wms.wms.data.model.request.LoginRequest
import com.wms.wms.data.model.response.LoginResponse
import com.wms.wms.data.model.response.ApiResult
import java.util.*

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */


class LoginDataSource {
    suspend fun login(baseUrl: String, username: String, password: String): ApiResult<LoginResponse> {
        var retrofit = RetrofitClient.getInstance(baseUrl)
        var apiInterface = retrofit.create(IApi::class.java)

        try {
            val response = apiInterface.login(LoginRequest(username, password))

            return if (response.isSuccessful) {
                var cookie = response.headers()["Set-Cookie"]

                if (cookie !== null) {
                    //your code for handaling success response
                    val data = response.body()!!

                    val current = Calendar.getInstance().time
                    UserManager.login(
                        data.username,
                        data.accessToken,
                        current.time + (1000 * 60 * 60),
                        cookie
                    )
                    return ApiResult.Success(data)
                }
                return ApiResult.RequestError(

                    ExceptionResponse(
                        false, false, 1, 1, "Cookie is empty", "", "",
                        false, false, false, false, false
                    )
                )
            } else {
                //todo: new exception to raise
               return ApiResult.RequestError(
                    ExceptionResponse(
                        false, false, 1, 1, "", "", "",
                        false, false, false, false, false
                    )
                )
            }
        } catch (Ex: Exception) {
            return ApiResult.Error(Ex)
            Log.e("Error", Ex.localizedMessage)
        }
    }
}