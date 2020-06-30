package com.ketechsoft.reqtrackmobileapp.ui.detailcomplaint

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.ketechsoft.reqtrackmobileapp.R
import com.ketechsoft.reqtrackmobileapp.model.ComplaintItem
import com.ketechsoft.reqtrackmobileapp.util.decodeImage
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    private lateinit var complaintItem: ComplaintItem
    private lateinit var map: GoogleMap
    private lateinit var coordinat: LatLng
    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        map = googleMap
        googleMap.addMarker(MarkerOptions().position(coordinat).title("Marker in Sydney"))
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coordinat, 16f))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
        imgSelected1.visibility = View.GONE
        imgSelected2.visibility = View.GONE
        imgSelected3.visibility = View.GONE
        val imgList: List<ImageView> = arrayListOf(imgSelected1, imgSelected2, imgSelected3)
        arguments?.let {
            complaintItem = DetailFragmentArgs.fromBundle(it).customComplaint
            val split = complaintItem.location.split(",")
            coordinat = LatLng(split[0].toDouble(), split[1].toDouble())
            dtTxtDescription.setText(complaintItem.description)
            dtTxtStatus.setText(complaintItem.complaintStatus.name)
            for (x in 0..complaintItem.complaintGalleries.size - 1) {
                imgList[x].visibility = View.VISIBLE
                decodeImage(complaintItem.complaintGalleries[x].imageUrl, imgList[x], resources)
            }
        }

        btnback.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(DetailFragmentDirections.actionDetailFragmentToComplaintFragment())

        }
        toolbar2.setNavigationOnClickListener {
            Navigation.findNavController(it)
                .navigate(DetailFragmentDirections.actionDetailFragmentToComplaintFragment())

        }

    }
}