package com.wms.wms.data.api.ReceivingList

import android.util.Log
import com.wms.wms.data.api.IApi
import com.wms.wms.data.api.RetrofitClient
import com.wms.wms.data.model.request.ReceivingDetailListRequest
import com.wms.wms.data.model.response.ExceptionResponse
import com.wms.wms.data.model.request.ReceivingListRequest
import com.wms.wms.data.model.response.ApiResult
import com.wms.wms.data.model.response.ReceivingDetailListResponse
import com.wms.wms.data.model.response.ReceivingListResponse

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */


class ReceivingDetailListDataSource {
    suspend fun GetReceivingList(ReceivingID: String): ApiResult<List<ReceivingDetailListResponse>> {
        var retrofit = RetrofitClient.getInstance()
        var apiInterface = retrofit.create(IApi::class.java)

        try {
            val response = apiInterface.receivingDetailList(ReceivingDetailListRequest(ReceivingID))

            return if (response.isSuccessful) {

                    //your code for handaling success response
                    val data = response.body()!!
                    return ApiResult.Success(data)
            } else {
                //todo: new exception to raise
               return ApiResult.RequestError(
                    ExceptionResponse(
                        false, false, 1, 1, "", "", "",
                        false, false, false, false, false
                    )
                )
            }
        } catch (Ex: Exception) {
            return ApiResult.Error(Ex)
            Log.e("Error", Ex.localizedMessage)
        }
    }
}