package com.wms.wms.ui.receivingDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wms.wms.data.api.ReceivingList.ReceivingDetailListApi
import com.wms.wms.data.api.ReceivingList.ReceivingDetailListDataSource

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class ReceivingDetailsViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReceivingDetailsViewModel::class.java)) {
            return ReceivingDetailsViewModel(
                ReceivingDetailListApi(
                    dataSource = ReceivingDetailListDataSource()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}