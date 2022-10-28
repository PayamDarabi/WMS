package com.wms.wms.data.api.login

import com.wms.wms.data.UserManager
import com.wms.wms.data.api.RetrofitClient
import com.wms.wms.data.model.request.LoginRequest
import com.wms.wms.data.model.response.ApiResult
import com.wms.wms.data.model.response.LoginResponse
import java.util.*


/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */


class LoginDataSource {
    suspend fun login(username: String, password: String): ApiResult<LoginResponse> {
        var apiInterface = RetrofitClient.getInstance()

        return try {
            val response = apiInterface.login(LoginRequest(username, password))
            val apiResult = ApiResult(response)

            if (apiResult.isSuccessful) {
                var cookie = response.headers()["Set-Cookie"]
                val data = apiResult.data

                if (cookie !== null && data !== null) {
                    //your code for handaling success response

                    val current = Calendar.getInstance().time
                    UserManager.login(
                        data.username,
                        data.fullname,
                        data.accessToken,
                        current.time + (1000 * 60 * 60),
                        cookie
                    )
                } else {
                    return ApiResult(
                        Exception(
                            "Cookie is empty"
                        )
                    )
                }
            }
            apiResult
        } catch (Ex: Exception) {
            return ApiResult(Ex)
        }
    }
}