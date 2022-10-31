package com.wms.wms.data.api

import com.wms.wms.data.model.request.*
import com.wms.wms.data.model.response.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST



interface IApi {
    @Headers("Content-Type: application/json")
    @POST("Login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>

    @Headers("Content-Type: application/json")
    @POST("ReceivingList")
    suspend fun receivingList(
        @Body receivingListRequest: ReceivingListRequest
    ): Response<ArrayList<ReceivingListResponse>>


    @Headers("Content-Type: application/json")
    @POST("ReceivingDetailList")
    suspend fun receivingDetailList(
        @Body receivingListRequest: ReceivingDetailListRequest
    ): Response<List<ReceivingDetailListResponse>>

    @Headers("Content-Type: application/json")
    @POST("ReceivingDetailCount")
    suspend fun receivingDetailCount(
        @Body receivingDetailCountRequest: ReceivingDetailCountRequest
    ): Response<ReceivingDetailCountResponse>


    @Headers("Content-Type: application/json")
    @POST("ReceivingDetailScanList")
    suspend fun receivingDetailScanList(
        @Body receivingDetailCountRequest: ReceivingDetailScanListRequest
    ): Response<ReceivingDetailScanListResponse>
}
