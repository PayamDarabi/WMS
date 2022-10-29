package com.wms.wms.ui.receivingDetailsCount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wms.wms.data.api.ReceivingDetailCount.ReceivingDetailCountApi
import com.wms.wms.data.api.ReceivingDetailCount.ReceivingDetailCountDataSource

class ReceivingDetailsCountViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReceivingDetailsCountViewModel::class.java)) {
            return ReceivingDetailsCountViewModel(
                receivingDetailCountApi = ReceivingDetailCountApi(
                    dataSource = ReceivingDetailCountDataSource()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}