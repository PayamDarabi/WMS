package com.wms.wms.ui.receiving

import android.R.attr
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.wms.wms.R
import com.wms.wms.databinding.FragmentReceivingBinding


class ReceivingFragment : Fragment() {
    private var _binding: FragmentReceivingBinding? = null
    private val binding get() = _binding!!
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var receivingList: ArrayList<ReceivingView>
    lateinit var receivingRw: RecyclerView
    lateinit var receivingAdapter: ReceivingAdapter
    var fullnames: ArrayList<String> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val receivingViewModel =
            ViewModelProvider(this, ReceivingViewModelFactory())[ReceivingViewModel::class.java]

        _binding = FragmentReceivingBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val loading = root.findViewById<ProgressBar>(R.id.receiving_loading)
        val itemsCount = root.findViewById<TextView>(R.id.txtReceivingItemsCount)
        val searchReceiving = root.findViewById<AutoCompleteTextView>(R.id.searchReceiving)

        receivingRw = root.findViewById(R.id.receiving_recyclerview)
        receivingRw.layoutManager = LinearLayoutManager(activity)
        swipeRefreshLayout = root.findViewById(R.id.container)
        lifecycleScope.launchWhenCreated {
            receivingViewModel.getReceivingList()
        }

        swipeRefreshLayout.setOnRefreshListener {

            // on below line we are setting is refreshing to false.
            swipeRefreshLayout.isRefreshing = false
            lifecycleScope.launchWhenCreated {
                receivingViewModel.getReceivingList()
            }
        }
        searchReceiving.setOnItemClickListener { parent, view, position, id ->
            searchReceiving.clearFocus()
            filter(fullnames[position])
        }
        searchReceiving.doOnTextChanged { text, start, before, count ->
            filter(text.toString())
        }
        receivingViewModel.receivingResult.observe(viewLifecycleOwner, Observer {
            val receivingResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (receivingResult.error != null) {
                Toast.makeText(
                    requireActivity(),
                    getString(R.string.no_receiving_found),
                    Toast.LENGTH_LONG
                ).show()
            }
            if (receivingResult.success != null) {
                receivingList = receivingResult.success
                receivingAdapter = ReceivingAdapter(receivingResult.success)
                itemsCount.text = "You Have " + receivingResult.success.size.toString() + " Items"
                receivingRw.adapter = receivingAdapter
                for (item in receivingList) {
                    fullnames.add(item.driverFullName)
                }
                val adapter = context?.let { it1 ->
                    ArrayAdapter(
                        it1,
                        android.R.layout.simple_list_item_1, fullnames
                    )
                }
                searchReceiving.setAdapter(adapter)
            }
        })
        return root
    }

    private fun filter(text: String) {
        // creating a new array list to filter our data.
        val filteredlist: ArrayList<ReceivingView> = ArrayList()

        // running a for loop to compare elements.
        for (item in receivingList) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.driverFullName.toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item)
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            receivingAdapter.filterList(receivingList)

        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            receivingAdapter.filterList(filteredlist)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}