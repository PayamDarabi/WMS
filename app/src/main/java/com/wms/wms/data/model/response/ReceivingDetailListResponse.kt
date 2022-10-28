package com.wms.wms.data.model.response

import com.google.gson.annotations.SerializedName

data class ReceivingDetailListResponse(
    @SerializedName("WorkerTaskID")
    val workerTaskID: String,
    @SerializedName("WorkerID")
    val workerID: String,
    @SerializedName("ProductTitle")
    val productTitle: String,
    @SerializedName("ProductCode")
    val productCode: String,
    @SerializedName("PlaqueNumber")
    val plaqueNumber: String?,
    @SerializedName("Serializable")
    val serializable: Boolean,
    @SerializedName("WarehouseCode")
    val warehouseCode: String?,
    @SerializedName("InvTypeID")
    val InvTypeID: Long,
    @SerializedName("InvTypeTitle")
    val invTypeTitle: Long,
    @SerializedName("ReceivingDetailID")
    val receivingDetailID: String,
)