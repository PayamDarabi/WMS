package com.wms.wms.data.api.ReceivingList

import com.wms.wms.data.api.login.LoginDataSource
import com.wms.wms.data.model.response.LoginResponse
import com.wms.wms.data.model.response.ApiResult
import com.wms.wms.data.model.response.ReceivingListResponse

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class ReceivingListApi(val dataSource: ReceivingListDataSource) {
    suspend fun GetReceivingList(): ApiResult<List<ReceivingListResponse>>? {
        return dataSource.GetReceivingList()
    }
}