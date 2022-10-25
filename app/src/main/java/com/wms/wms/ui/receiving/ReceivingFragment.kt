package com.wms.wms.ui.receiving

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wms.wms.R
import com.wms.wms.databinding.FragmentReceivingBinding

class ReceivingFragment : Fragment() {
    private var _binding: FragmentReceivingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val receivingViewModel =
            ViewModelProvider(this,ReceivingViewModelFactory())[ReceivingViewModel::class.java]

        _binding = FragmentReceivingBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val loading = root.findViewById<ProgressBar>(R.id.receiving_loading)

        val recyclerview = root.findViewById<RecyclerView>(R.id.receiving_recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(activity)

        lifecycleScope.launchWhenCreated {
            receivingViewModel.getReceivingList()
        }

        activity?.let { ac ->
            receivingViewModel.receivingResult.observe(ac, Observer {
                val receivingResult = it ?: return@Observer

                loading.visibility = View.GONE
                if (receivingResult.error != null) {
                    Toast.makeText(ac, getString(R.string.no_receiving_found), Toast.LENGTH_LONG)
                        .show()
                }
                if (receivingResult.success != null) {
                    Toast.makeText(ac, "Found", Toast.LENGTH_LONG)
                        .show()

                    val adapter = ReceivingAdapter(receivingResult.success)
                    recyclerview.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
            })
        }

       /* val cardReceiving = binding.toReceiptingDetails
        cardReceiving.setOnClickListener {
            findNavController().navigate(
                R.id.nav_receiving_details, null, null
            )
        }*/
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}