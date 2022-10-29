package com.wms.wms.data.api.ReceivingList

import com.wms.wms.data.model.response.ApiResult
import com.wms.wms.data.model.response.ReceivingDetailListResponse

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class ReceivingDetailListApi(val dataSource: ReceivingDetailListDataSource) {
    suspend fun get(ReceivingID: String): ApiResult<List<ReceivingDetailListResponse>> {
        return dataSource.getReceivingDetailList(ReceivingID)
    }
}