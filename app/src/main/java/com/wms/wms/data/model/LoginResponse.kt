package com.wms.wms.data.model

import com.google.gson.annotations.SerializedName

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoginResponse(
    @SerializedName("UserName")
    val username: String,
    @SerializedName("FullName")
    val fullname: String,
    @SerializedName("TokenID")
    val accessToken: String
)
