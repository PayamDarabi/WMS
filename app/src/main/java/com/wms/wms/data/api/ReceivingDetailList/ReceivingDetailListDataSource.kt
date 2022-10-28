package com.wms.wms.data.api.ReceivingList

import com.wms.wms.data.api.RetrofitClient
import com.wms.wms.data.model.request.ReceivingDetailListRequest
import com.wms.wms.data.model.response.ApiResult
import com.wms.wms.data.model.response.ReceivingDetailListResponse

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */


class ReceivingDetailListDataSource {
    suspend fun GetReceivingList(ReceivingID: String): ApiResult<List<ReceivingDetailListResponse>> {
        var apiInterface = RetrofitClient.getInstance()

        return try {
            val response = apiInterface.receivingDetailList(ReceivingDetailListRequest(ReceivingID))
            ApiResult(response)
        } catch (Ex: Exception) {
            ApiResult(Ex)
        }
    }
}