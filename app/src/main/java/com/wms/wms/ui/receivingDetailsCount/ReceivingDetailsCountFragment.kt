package com.wms.wms.ui.receivingDetailsCount

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.wms.wms.databinding.FragmentReceivingDetailsBinding


class ReceivingDetailsCountFragment : DialogFragment() {
    private var _binding: FragmentReceivingDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val receivingDetailsCountViewModel =
            ViewModelProvider(this, ReceivingDetailsCountViewModelFactory())[ReceivingDetailsCountViewModel::class.java]

        _binding = FragmentReceivingDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }
}