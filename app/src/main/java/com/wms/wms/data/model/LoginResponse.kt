package com.wms.wms.data.model
import com.google.gson.annotations.SerializedName

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoginResponse(
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
