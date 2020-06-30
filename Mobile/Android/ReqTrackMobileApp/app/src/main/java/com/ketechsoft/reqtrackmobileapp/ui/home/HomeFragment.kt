package com.ketechsoft.reqtrackmobileapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.ketechsoft.reqtrackmobileapp.R
import com.ketechsoft.reqtrackmobileapp.databinding.FragmentHomeBinding


class HomeFragment : Fragment(R.layout.fragment_home) {
    private var fragmentHomeBinding: FragmentHomeBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)
        fragmentHomeBinding = binding

        binding.btnNewComplaint.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(HomeFragmentDirections.actionHomeFragmentToNewComplaintFragment())
        }
        binding.btnComplaint.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(HomeFragmentDirections.actionHomeFragmentToComplaintFragment())
        }
        binding.btnProfile.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(HomeFragmentDirections.actionHomeFragmentToProfileFragment())
        }


    }

}
