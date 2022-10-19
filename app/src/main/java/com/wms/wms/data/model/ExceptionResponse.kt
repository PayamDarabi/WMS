package com.wms.wms.data.model

import com.google.gson.annotations.SerializedName

data class ExceptionResponse(
    @SerializedName("IsSucceed")
    val isSucceed: Boolean,
    @SerializedName("UpdatedAny")
    val updatedAny: Boolean,
    @SerializedName("MessageCode")
    val messageCode: Long,
    @SerializedName("MessageType")
    val messageType: Long,
    @SerializedName("Messages")
    val messages: String,
    @SerializedName("ReturnValue")
    val returnValue: String,
    @SerializedName("EntityID")
    val entityId: Any?,
    @SerializedName("EnableUpdate")
    val enableUpdate: Boolean,
    @SerializedName("EnableInsert")
    val enableInsert: Boolean,
    @SerializedName("EnableClose")
    val enableClose: Boolean,
    @SerializedName("EnableConfirm")
    val enableConfirm: Boolean,
    @SerializedName("EntityStringKey")
    val entityStringKey: Any?,
)
