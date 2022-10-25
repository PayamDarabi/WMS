package com.wms.wms.ui.receiving

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wms.wms.data.api.ReceivingList.ReceivingListApi
import com.wms.wms.data.model.response.ApiResult

class ReceivingViewModel(private val receivingListApi: ReceivingListApi) : ViewModel() {

    private val _receivingResult = MutableLiveData<ReceivingResult>()
    val receivingResult: LiveData<ReceivingResult> = _receivingResult

    suspend fun getReceivingList() {
        // can be launched in a separate asynchronous job
        val result = receivingListApi.GetReceivingList()

        if (result is ApiResult.Success) {
            val receivingList = result.data.map {
                ReceivingView(
                    it.driverFullName,
                    it.dockCode,
                    it.receivingNumber,
                    it.containerNumber,
                    it.plaqueNumber,
                    it.carTypeId,
                    it.createdOn,
                    it.receivingId,
                    it.receivingTypeId
                )
            }
            _receivingResult.value = ReceivingResult(success =   receivingList)
        } else {
            _receivingResult.value = ReceivingResult()
        }
    }
}