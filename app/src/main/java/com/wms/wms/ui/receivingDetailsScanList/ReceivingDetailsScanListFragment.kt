package com.wms.wms.ui.receivingDetailsScanList

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.wms.wms.R
import com.wms.wms.data.model.request.ReceivingDetailCountRequest
import com.wms.wms.data.model.request.ReceivingDetailScanListRequest
import com.wms.wms.databinding.FragmentReceivingDetailsCountBinding
import com.wms.wms.databinding.FragmentReceivingDetailsScanListBinding


class ReceivingDetailsScanListFragment : AppCompatDialogFragment() {
    private var _binding: FragmentReceivingDetailsScanListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val receivingDetailsCountViewModel =
            ViewModelProvider(
                this,
                ReceivingDetailsScanListViewModelFactory()
            )[ReceivingDetailsScanListViewModel::class.java]

        _binding = FragmentReceivingDetailsScanListBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val imgCLose: ImageView = root.findViewById<ImageView>(R.id.imgClose)
        val btnCancel: Button = root.findViewById(R.id.btnCancel)
        val btnConfirm: Button = root.findViewById(R.id.btnConfirm)
        val edtQuantity: EditText = root.findViewById(R.id.edtQuantity)

        imgCLose.setOnClickListener {
            dialog?.dismiss()
        }
        btnCancel.setOnClickListener {
            dialog?.dismiss()
        }
        btnConfirm.setOnClickListener {
            lifecycleScope.launchWhenCreated {
                receivingDetailsCountViewModel.getItems(
                    ReceivingDetailScanListRequest(
                        ""
                    )
                )
            }
        }
        return root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle)
    }
}