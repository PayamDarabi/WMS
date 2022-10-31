package com.wms.wms.ui.receivingDetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.wms.wms.R
import com.wms.wms.ui.receivingDetailsCount.ReceivingDetailsCountFragment
import com.wms.wms.ui.receivingDetailsScanList.ReceivingDetailsScanListFragment


class DetailsAdapter(private val mList: List<ItemsViewModel>, fm: FragmentManager) : RecyclerView.Adapter<DetailsAdapter.ViewHolder>() {
    val _fm: FragmentManager=fm
    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_receiving_detail, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class
        holder.imageView.setImageResource(itemsViewModel.image)

        // sets the text to the textview from our itemHolder class
        holder.textView.text = itemsViewModel.text
        holder.imgDetailsCount.setOnClickListener {
            ReceivingDetailsCountFragment().show(_fm,"")
        }

        holder.imgScanBarcode.setOnClickListener {
            ReceivingDetailsScanListFragment().show(_fm,"")
        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val textView: TextView = itemView.findViewById(R.id.textView)
        val imgDetailsCount: ImageView = itemView.findViewById(R.id.imgDetailsCount)
        val imgScanBarcode: ImageView = itemView.findViewById(R.id.imgScanBarcode)
    }
}
