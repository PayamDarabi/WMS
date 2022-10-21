package com.wms.wms.ui.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.wms.wms.R
import com.wms.wms.databinding.FragmentHomeBinding
import com.wms.wms.ui.receiving.ReceivingFragment
import java.time.Duration

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val cardReceiving: CardView = binding.cardReceiving
        cardReceiving.setOnClickListener {
            findNavController().navigate(R.id.nav_receiving, null, NavOptions.Builder()
                .setPopUpTo(R.id.nav_home, true)
                .build())
       }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}