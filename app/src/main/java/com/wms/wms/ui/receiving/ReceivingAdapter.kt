package com.wms.wms.ui.receiving

    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import android.widget.TextView
    import androidx.recyclerview.widget.RecyclerView
    import com.wms.wms.R

class ReceivingAdapter(private var receivingList: List<ReceivingView>) : RecyclerView.Adapter<ReceivingAdapter.ViewHolder>() {
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

            val receivingView = receivingList[position]

            // sets the image to the imageview from our itemHolder class
            holder.txtDriverFullName.text = receivingView.driverFullName
            val plaqueDeparted = receivingView.plaqueNumber.split('-')

            var plaqueChar = plaqueDeparted[0]
            val plaqueIr = plaqueDeparted[1]
            val plaqueMain1 = plaqueDeparted[2]
            var plaqueMain2 = plaqueDeparted[3]

            holder.txtCarPlaqueMain.text = "$plaqueMain2 $plaqueMain1 $plaqueChar"
            holder.txtCarStateNumber.text = plaqueIr

            val splitDateTime= receivingView.createdOn.split('T')
            val date = splitDateTime[0]
            val time= splitDateTime[1].split('.')[0]
            // sets the text to the textview from our itemHolder class
            holder.txtDockCode.text = receivingView.dockCode
            holder.txtReceivingNumber.text = receivingView.receivingNumber
            holder.txtContainerNumber.text = receivingView.containerNumber
            holder.txtCreatedOn.text = "$date $time"
            holder.txtCarTypeTitle.text = receivingView.carTypeTitle.toString()
        }

        // return the number of the items in the list
        override fun getItemCount(): Int {
            return receivingList.size
        }

    fun filterList(filterlist: ArrayList<ReceivingView>) {
        // below line is to add our filtered
        // list in our course array list.
        receivingList = filterlist
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged()
    }
    // Holds the views for adding it to image and text
        class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
            val txtDriverFullName: TextView = itemView.findViewById(R.id.txtDriverFullName)
            val txtDockCode: TextView = itemView.findViewById(R.id.txtDockCode)
            val txtCarPlaqueMain: TextView = itemView.findViewById(R.id.car_plaque_main)
            val txtCarStateNumber: TextView = itemView.findViewById(R.id.car_state_number)
            val txtReceivingNumber: TextView = itemView.findViewById(R.id.txtReceivingNumber)
            val txtContainerNumber: TextView = itemView.findViewById(R.id.txtContainerNumber)
            val txtCreatedOn: TextView = itemView.findViewById(R.id.txtCreatedOn)
            val txtCarTypeTitle: TextView = itemView.findViewById(R.id.txtCarTypeTitle)
        }

}