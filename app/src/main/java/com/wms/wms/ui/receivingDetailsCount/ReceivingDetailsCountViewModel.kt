package com.wms.wms.ui.receivingDetailsCount

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wms.wms.data.api.ReceivingDetailCount.ReceivingDetailCountApi
import com.wms.wms.data.model.request.ReceivingDetailCountRequest

class ReceivingDetailsCountViewModel(private val receivingDetailCountApi: ReceivingDetailCountApi) : ViewModel() {

    private val _receivingDetailsCountResult = MutableLiveData<ReceivingDetailsCountResult>()
    val receivingDetailsCountResult: LiveData<ReceivingDetailsCountResult> = _receivingDetailsCountResult

    suspend fun postReceivingDetailCount(receivingDetailCountRequest: ReceivingDetailCountRequest) {
        val result = receivingDetailCountApi.postReceivingDetailCount(receivingDetailCountRequest)
        if (result.isSuccessful && result.data !==null) {
            val receivingDetailCountResponse =  ReceivingDetailsCountView(message = result.data!!.c)
            _receivingDetailsCountResult.value = ReceivingDetailsCountResult(success = receivingDetailCountResponse)
        } else {
            _receivingDetailsCountResult.value = ReceivingDetailsCountResult()
        }
    }
}