package com.wms.wms.data.api.ReceivingList

import android.util.Log
import com.wms.wms.data.UserManager
import com.wms.wms.data.api.IApi
import com.wms.wms.data.api.RetrofitClient
import com.wms.wms.data.model.response.ExceptionResponse
import com.wms.wms.data.model.request.LoginRequest
import com.wms.wms.data.model.request.ReceivingListRequest
import com.wms.wms.data.model.response.LoginResponse
import com.wms.wms.data.model.response.ApiResult
import com.wms.wms.data.model.response.ReceivingListResponse
import java.util.*

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */


class ReceivingListDataSource {
    suspend fun GetReceivingList(): ApiResult<List<ReceivingListResponse>> {
        var retrofit = RetrofitClient.getInstance()
        var apiInterface = retrofit.create(IApi::class.java)

        try {
            val response = apiInterface.receivingList(ReceivingListRequest())

            return if (response.isSuccessful) {
                var cookie = response.headers()["Set-Cookie"]

                if (cookie !== null) {
                    //your code for handaling success response
                    val data = response.body()!!
                    return ApiResult.Success(listOf(data))
                }
                return ApiResult.RequestError(

                    ExceptionResponse(
                        false, false, 1, 1, "Cookie is empty", "", "",
                        false, false, false, false, false
                    )
                )
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