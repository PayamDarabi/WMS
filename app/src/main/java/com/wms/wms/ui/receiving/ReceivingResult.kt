package com.wms.wms.ui.receiving

data class ReceivingResult (
    val success: List<ReceivingView>? = null,
    val error: Int? = null
)