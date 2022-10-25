package com.wms.wms.ui.login

import com.google.gson.annotations.SerializedName

/**
 * User details post authentication that is exposed to the UI
 */
data class LoggedInUserView(
    val username: String,
    val fullname: String,
    val accessToken: String
)