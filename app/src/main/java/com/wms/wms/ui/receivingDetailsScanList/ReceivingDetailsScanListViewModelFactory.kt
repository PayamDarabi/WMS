package com.wms.wms.ui.receivingDetailsScanList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wms.wms.data.api.ReceivingDetailCount.ReceivingDetailCountApi
import com.wms.wms.data.api.ReceivingDetailCount.ReceivingDetailCountDataSource
import com.wms.wms.data.api.ReceivingList.ReceivingDetailScanListApi
import com.wms.wms.data.api.ReceivingList.ReceivingDetailScanListDataSource

class ReceivingDetailsScanListViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReceivingDetailsScanListViewModel::class.java)) {
            return ReceivingDetailsScanListViewModel(
                receivingDetailScanListApi = ReceivingDetailScanListApi(
                    dataSource = ReceivingDetailScanListDataSource()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}