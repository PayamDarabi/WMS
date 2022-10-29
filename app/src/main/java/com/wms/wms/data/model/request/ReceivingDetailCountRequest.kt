package com.wms.wms.data.model.request

data class ReceivingDetailCountRequest(
   val ReceivingDetailID: String,
   val Quantity: Int,
   val WorkerTaskID: String,
)