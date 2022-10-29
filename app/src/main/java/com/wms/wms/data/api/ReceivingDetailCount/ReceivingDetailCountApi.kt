package com.wms.wms.data.api.ReceivingDetailCount

import com.wms.wms.data.api.ReceivingList.ReceivingDetailListDataSource
import com.wms.wms.data.model.request.ReceivingDetailCountRequest
import com.wms.wms.data.model.response.ApiResult
import com.wms.wms.data.model.response.ReceivingDetailCountResponse
import com.wms.wms.data.model.response.ReceivingDetailListResponse

class ReceivingDetailCountApi(val dataSource: ReceivingDetailCountDataSource) {
    suspend fun postReceivingDetailCount(receivingDetailCountRequest: ReceivingDetailCountRequest): ApiResult<ReceivingDetailCountResponse> {
        return dataSource.postReceivingDetailCount(receivingDetailCountRequest)
    }
}