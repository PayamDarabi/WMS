package com.wms.wms.ui.receiving

    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import android.widget.TextView
    import androidx.recyclerview.widget.RecyclerView
    import com.wms.wms.R

class ReceivingAdapter(private val mList: List<ReceivingView>) : RecyclerView.Adapter<ReceivingAdapter.ViewHolder>() {

        // create new views
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            // inflates the card_view_design view
            // that is used to hold list item
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_receiving, parent, false)

            return ViewHolder(view)
        }

        // binds the list items to a view
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            val receivingView = mList[position]

            // sets the image to the imageview from our itemHolder class
            holder.txtDriverFullName.text = receivingView.driverFullName

            // sets the text to the textview from our itemHolder class
            holder.txtDockCode.text = receivingView.dockCode

        }

        // return the number of the items in the list
        override fun getItemCount(): Int {
            return mList.size
        }

        // Holds the views for adding it to image and text
        class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
            val txtDriverFullName: TextView = itemView.findViewById(R.id.txtDriverFullName)
            val txtDockCode: TextView = itemView.findViewById(R.id.txtDockCode)
        }

}