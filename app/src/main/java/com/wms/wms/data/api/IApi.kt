package com.wms.wms.data.api

import com.wms.wms.data.model.LoginRequest
import com.wms.wms.data.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface IApi {
    @Headers("Content-Type: application/json")
    @POST("Login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>
}