package com.ketechsoft.reqtrackmobileapp.ui.complaint

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ketechsoft.reqtrackmobileapp.R
import kotlinx.android.synthetic.main.fragment_complaint.*


class ComplaintFragment : Fragment(R.layout.fragment_complaint) {

    private lateinit var viewModel: ComplaintViewModel
    private var adapter: ComplaintAdapter = ComplaintAdapter(arrayListOf())
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ComplaintViewModel::class.java)

        viewModel.getData(requireContext())
        observeData()

        val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            Navigation.findNavController(view)
                .navigate(ComplaintFragmentDirections.actionComplaintFragmentToHomeFragment())

        }

    }

    fun observeData() {
        viewModel.complaintData.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it.size == 0) {
                    txtComplaint.text = "BAŞVURUNUZ BULUNMAMAKTADIR"
                    txtComplaint.textSize = 26f
                    txtComplaint.visibility = View.VISIBLE
                } else {
                    txtComplaint.visibility = View.GONE
                    rcComplaint.adapter = ComplaintAdapter(it)
                    rcComplaint.layoutManager = LinearLayoutManager(requireContext())
                }

            }


        })
    }
}


