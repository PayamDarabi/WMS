package com.wms.wms.ui.receivingDetailsScanList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wms.wms.data.api.ReceivingDetailCount.ReceivingDetailCountApi
import com.wms.wms.data.api.ReceivingList.ReceivingDetailScanListApi
import com.wms.wms.data.model.request.ReceivingDetailCountRequest
import com.wms.wms.data.model.request.ReceivingDetailScanListRequest
import com.wms.wms.data.model.response.ReceivingDetailScanListResponse

class ReceivingDetailsScanListViewModel(private val receivingDetailScanListApi: ReceivingDetailScanListApi) : ViewModel() {

    private val _receivingDetailsScanListResult = MutableLiveData<ReceivingDetailsScanListResult>()
    val receivingDetailsScanListResult: LiveData<ReceivingDetailsScanListResult> = _receivingDetailsScanListResult

    suspend fun getItems(receivingDetailScanListRequest: ReceivingDetailScanListRequest) {
        val result = receivingDetailScanListApi.getItems()
        if (result.isSuccessful && result.data !==null) {
            val receivingDetailsScanListView =  ReceivingDetailsScanListView(result.data!!.c)
            _receivingDetailsScanListResult.value = ReceivingDetailsScanListResult(success = receivingDetailsScanListView)
        } else {
            _receivingDetailsScanListResult.value = ReceivingDetailsScanListResult()
        }
    }
}