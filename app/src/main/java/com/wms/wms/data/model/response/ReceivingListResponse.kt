package com.wms.wms.data.model.response

import com.google.gson.annotations.SerializedName

data class ReceivingListResponse(
    @SerializedName("TokenID")
    val accessToken: String
)