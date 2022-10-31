package com.wms.wms.data.api.ReceivingList

import com.wms.wms.data.api.RetrofitClient
import com.wms.wms.data.model.request.ReceivingDetailListRequest
import com.wms.wms.data.model.request.ReceivingDetailScanListRequest
import com.wms.wms.data.model.response.ApiResult
import com.wms.wms.data.model.response.ReceivingDetailListResponse
import com.wms.wms.data.model.response.ReceivingDetailScanListResponse

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */


class ReceivingDetailScanListDataSource {
    suspend fun getItemslList(): ApiResult<ReceivingDetailScanListResponse> {
        var apiInterface = RetrofitClient.getInstance()

        return try {
            val response = apiInterface.receivingDetailScanList(ReceivingDetailScanListRequest(""))
            ApiResult(response)
        } catch (Ex: Exception) {
            ApiResult(Ex)
        }
    }
}