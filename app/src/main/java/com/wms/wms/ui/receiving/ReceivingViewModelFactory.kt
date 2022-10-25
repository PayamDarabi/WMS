package com.wms.wms.ui.receiving

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wms.wms.data.api.ReceivingList.ReceivingListApi
import com.wms.wms.data.api.ReceivingList.ReceivingListDataSource

class ReceivingViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReceivingViewModel::class.java)) {
            return ReceivingViewModel(
                receivingListApi = ReceivingListApi(
                    dataSource = ReceivingListDataSource()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}