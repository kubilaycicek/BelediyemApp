package com.ketechsoft.reqtrackmobileapp.ui.complaint

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ketechsoft.reqtrackmobileapp.R
import com.ketechsoft.reqtrackmobileapp.model.ComplaintItem
import kotlinx.android.synthetic.main.adapter_complaint.view.*

class ComplaintAdapter(private val complaintList: ArrayList<ComplaintItem>) :
    RecyclerView.Adapter<ComplaintViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComplaintViewHolder {
        return ComplaintViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return complaintList.size
    }

    override fun onBindViewHolder(holder: ComplaintViewHolder, position: Int) {
        holder.bind(complaintList[position])
    }

    fun updateList(newList: ArrayList<ComplaintItem>) {
        complaintList.clear()
        complaintList.addAll(newList)
        notifyDataSetChanged()
    }
}

class ComplaintViewHolder(itemView: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(itemView.context).inflate(
        R.layout.adapter_complaint, itemView, false
    )
) {

    fun bind(complaint: ComplaintItem) {
        itemView.txtComplaintMesssage.text = complaint.description
        itemView.txtComplaintStatu.text = complaint.complaintStatus.name
        itemView.cardViewComplaint.setOnClickListener {
            it?.let {
                Navigation.findNavController(it).navigate(
                    ComplaintFragmentDirections.actionComplaintFragmentToDetailFragment(complaint)
                )
            }
        }
    }

}
