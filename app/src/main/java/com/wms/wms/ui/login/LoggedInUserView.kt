package com.wms.wms.ui.login

import com.google.gson.annotations.SerializedName

/**
 * User details post authentication that is exposed to the UI
 */
data class LoggedInUserView(
    val isSucceed: Boolean,
    val updatedAny: Boolean,
    val messageCode: Long,
    val messageType: Long,
    val messages: String,
    val returnValue: String,
    val entityId: Any?,
    val enableUpdate: Boolean,
    val enableInsert: Boolean,
    val enableClose: Boolean,
    val enableConfirm: Boolean,
    val entityStringKey: Any?,
)