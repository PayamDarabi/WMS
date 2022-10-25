package com.wms.wms.data.model.response
import com.google.gson.annotations.SerializedName

data class ReceivingListResponse(
    @SerializedName("DriverFullName")
    val driverFullName: String,
    @SerializedName("DockCode")
    val dockCode: String,
    @SerializedName("ReceivingNumber")
    val receivingNumber: String,
    @SerializedName("ContainerNumber")
    val containerNumber: String,
    @SerializedName("PlaqueNumber")
    val plaqueNumber: String,
    @SerializedName("CarTypeID")
    val carTypeId: Long?,
    @SerializedName("CreatedOn")
    val createdOn: String,
    @SerializedName("ReceivingID")
    val receivingId: String,
    @SerializedName("ReceivingTypeID")
    val receivingTypeId: Long,
)