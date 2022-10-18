package com.wms.wms.data

import android.util.Log
import com.wms.wms.data.api.IApi
import com.wms.wms.data.api.RetrofitClient
import com.wms.wms.data.model.LoginRequest
import com.wms.wms.data.model.Result
import com.wms.wms.data.model.LoginResponse
import kotlinx.coroutines.runBlocking

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */


class LoginDataSource {
    suspend fun login(baseUrl:String, username: String, password: String): Result<LoginResponse> {
        var retrofit = RetrofitClient.getInstance(baseUrl)
        var apiInterface = retrofit.create(IApi::class.java)

        try {
            val response = apiInterface.login(LoginRequest(username, password))
            return if (response.isSuccessful()) {
                //your code for handaling success response
                val data = response.body()!!
                Result.Success(data)
            } else {
                //todo: new exception to raise
                Result.Success(LoginResponse(false,false,1,1,"","","",
                    false,false,false,false,false))
            }
        } catch (Ex: Exception) {
            return Result.Error(Ex)
            Log.e("Error", Ex.localizedMessage)
        }
    }
    fun logout() {
        // TODO: revoke authentication
    }
}