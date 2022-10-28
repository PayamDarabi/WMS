package com.wms.wms.data.model.response

import com.google.gson.annotations.SerializedName

data class ExceptionResponse(
    @SerializedName("IsSucceed")
    val isSucceed: Boolean? = null,
    @SerializedName("UpdatedAny")
    val updatedAny: Boolean? = null,
    @SerializedName("MessageCode")
    val messageCode: Long? = null,
    @SerializedName("MessageType")
    val messageType: Long? = null,
    @SerializedName("Message")
    val message: String,
    @SerializedName("Messages")
    val messages: String? = null,
    @SerializedName("ReturnValue")
    val returnValue: String? = null,
    @SerializedName("EntityID")
    val entityId: Any? = null,
    @SerializedName("EnableUpdate")
    val enableUpdate: Boolean? = null,
    @SerializedName("EnableInsert")
    val enableInsert: Boolean? = null,
    @SerializedName("EnableClose")
    val enableClose: Boolean? = null,
    @SerializedName("EnableConfirm")
    val enableConfirm: Boolean? = null,
    @SerializedName("EntityStringKey")
    val entityStringKey: Any? = null,
)
