package com.wms.wms.ui.receiving

data class ReceivingView (
    val driverFullName: String,
    val dockCode: String,
    val receivingNumber: String,
    val containerNumber: String,
    val plaqueNumber: String,
    val carTypeId: Long?,
    val carTypeTitle: Long?,
    val createdOn: String,
    val receivingId: String,
    val receivingTypeId: Long
)