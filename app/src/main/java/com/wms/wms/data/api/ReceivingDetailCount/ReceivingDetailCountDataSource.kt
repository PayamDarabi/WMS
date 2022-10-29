package com.wms.wms.data.api.ReceivingDetailCount

import com.wms.wms.data.api.RetrofitClient
import com.wms.wms.data.model.request.ReceivingDetailCountRequest
import com.wms.wms.data.model.response.ApiResult
import com.wms.wms.data.model.response.ReceivingDetailCountResponse

class ReceivingDetailCountDataSource {
    suspend fun postReceivingDetailCount(receivingDetailCountRequest: ReceivingDetailCountRequest): ApiResult<ReceivingDetailCountResponse> {
        var apiInterface = RetrofitClient.getInstance()

        return try {
            val response = apiInterface.receivingDetailCount(receivingDetailCountRequest)
            ApiResult(response)
        } catch (Ex: Exception) {
            ApiResult(Ex)
        }
    }
}