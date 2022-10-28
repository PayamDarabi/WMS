package com.wms.wms.ui.receiving

data class ReceivingResult (
    val success: ArrayList<ReceivingView>? = null,
    val error: Int? = null
)