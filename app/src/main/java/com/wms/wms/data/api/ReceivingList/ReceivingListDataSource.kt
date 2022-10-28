package com.wms.wms.data.api.ReceivingList

import android.util.Log
import com.google.gson.Gson
import com.wms.wms.data.api.RetrofitClient
import com.wms.wms.data.model.request.ReceivingListRequest
import com.wms.wms.data.model.response.ApiResult
import com.wms.wms.data.model.response.ExceptionResponse
import com.wms.wms.data.model.response.ReceivingListResponse
import java.util.*

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */


class ReceivingListDataSource {
    suspend fun getReceivingList(): ApiResult<ArrayList<ReceivingListResponse>> {
        var apiInterface = RetrofitClient.getInstance()

        return try {
            val response = apiInterface.receivingList(ReceivingListRequest())
            ApiResult(response)
        } catch (Ex: Exception) {
            ApiResult(Ex)
        }
    }
}