package com.wms.wms.ui.receiving

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.wms.wms.R
import com.wms.wms.databinding.FragmentHomeBinding
import com.wms.wms.databinding.FragmentReceivingBinding
import com.wms.wms.ui.home.HomeViewModel

class ReceivingFragment : Fragment() {
    private var _binding: FragmentReceivingBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: ReceivingViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentReceivingBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}