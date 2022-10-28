package com.wms.wms.ui.receivingDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wms.wms.data.api.ReceivingList.ReceivingDetailListApi
import com.wms.wms.data.model.response.ReceivingDetailListResponse

class ReceivingDetailsViewModel(private val receivingDetailListApi: ReceivingDetailListApi) :
    ViewModel() {

    private val _list = MutableLiveData<List<ReceivingDetailListResponse>>()
    val list: LiveData<List<ReceivingDetailListResponse>> = _list

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    suspend fun fetchList(ReceivingID: String) {
        // can be launched in a separate asynchronous job
        val result = receivingDetailListApi.get(ReceivingID = ReceivingID)

        if (result.isSuccessful) {
            _list.value = result.data!!
        } else {
            _error.value = result.errorBody?.message ?: "Failed"
            //            _list.value = LoginResult(error = R.string.login_failed)
        }
    }
}