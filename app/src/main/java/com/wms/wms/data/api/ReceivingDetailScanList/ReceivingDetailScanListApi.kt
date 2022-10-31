package com.wms.wms.data.api.ReceivingList

import com.wms.wms.data.model.response.ApiResult
import com.wms.wms.data.model.response.ReceivingDetailListResponse
import com.wms.wms.data.model.response.ReceivingDetailScanListResponse

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class ReceivingDetailScanListApi(val dataSource: ReceivingDetailScanListDataSource) {
    suspend fun getItems(): ApiResult<ReceivingDetailScanListResponse> {
        return dataSource.getItemslList()
    }
}