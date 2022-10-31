package com.wms.wms.ui.receivingDetailsScanList

import com.wms.wms.ui.receivingDetailsCount.ReceivingDetailsCountView

data class ReceivingDetailsScanListResult(
    val success: ReceivingDetailsScanListView? =null,
    val error: Int? = null
)