package com.ketechsoft.reqtrackmobileapp.ui.newcomplaint


import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.ketechsoft.reqtrackmobileapp.R
import com.ketechsoft.reqtrackmobileapp.model.*
import com.ketechsoft.reqtrackmobileapp.util.encodeImage
import com.ketechsoft.reqtrackmobileapp.util.getID
import com.ketechsoft.reqtrackmobileapp.util.isOnline
import kotlinx.android.synthetic.main.customalert.*
import kotlinx.android.synthetic.main.custompb.*
import kotlinx.android.synthetic.main.fragment_new_complaint.*
import java.io.IOException

class NewComplaintFragment : Fragment(R.layout.fragment_new_complaint),
    GoogleMap.OnMarkerClickListener {


    private var locationManager: LocationManager? = null
    private var locationListener: LocationListener? = null

    private lateinit var viewModel: NewComplaintViewModel
    private lateinit var complaintGallery: List<ComplaintGallery>
    private var complaintImage1: ComplaintGallery? = null
    private var complaintImage2: ComplaintGallery? = null
    private var complaintImage3: ComplaintGallery? = null


    val test = ArrayList<ComplaintGallery>()
    var userLocation: LatLng? = null
    var lastUserLocation: LatLng? = null
    private var imageList: MutableList<Boolean>? = null
    private var count = 0

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var map: GoogleMap
    private lateinit var lastLocation: Location
    private lateinit var currentLatLng: LatLng

    private val callback = OnMapReadyCallback { googleMap ->

        locationManager = activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager?
        map = googleMap
        map.uiSettings.isZoomControlsEnabled = true
        map.setOnMarkerClickListener(this)
        setUpMap()

        object : LocationListener {
            override fun onLocationChanged(location: Location?) {
                if (location != null) {
                    googleMap.clear()
                    lastLocation = location
                    currentLatLng = LatLng(location.latitude, location.longitude)
                    placeMarkerOnMap(currentLatLng)
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15f))
                }
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
            }

            override fun onProviderEnabled(provider: String?) {
            }

            override fun onProviderDisabled(provider: String?) {
            }
        }
        map.setOnMapLongClickListener {
            it?.let {
                googleMap.clear()
                currentLatLng = LatLng(it.latitude, it.longitude)
                placeMarkerOnMap(currentLatLng)
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15f))
            }
        }


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        imageList = mutableListOf(false, false, false)

        complaintImage1 = ComplaintGallery(imageUrl = "")
        complaintImage2 = ComplaintGallery(imageUrl = "")
        complaintImage3 = ComplaintGallery(imageUrl = "")
        imgSelected1.visibility = View.GONE
        imgSelected3.visibility = View.GONE
        imgSelected2.visibility = View.GONE

        viewModel = ViewModelProvider(this).get(NewComplaintViewModel::class.java)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

        btnSelectPhoto.setOnClickListener {
            if (count == 3) {
                Toast.makeText(
                    requireContext(),
                    "En Fazla 3 Fotoğraf Ekleyebilirsiniz!",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                val alert = AlertDialog.Builder(requireContext())
                alert.setMessage("Lütfen Fotograf Yükleme Yonteminizi Seçiniz")
                alert.setView(View.inflate(requireContext(), R.layout.customalert, null))
                val dialog = alert.create()
                dialog.show()
                dialog.btnAlertGallery.setOnClickListener {
                    selectGallery()
                    dialog.dismiss()
                }

                dialog.btnAlertphoto.setOnClickListener {
                    takePhoto()
                    dialog.dismiss()
                }
                dialog.btnAlertExit.setOnClickListener {
                    dialog.dismiss()
                }

            }
        }
        btnNewComplaint.setOnClickListener {


            val description = dtTxtDescription.text.toString()
            if (description.isNotEmpty()) {
                if (count > 0) {
                    if (isOnline(requireContext())) {
                        postData(view)
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "LÜtfen İnternet Bağlantınızı Kontrol Ediniz",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Lütfen En Az 1 Fotoğraf Seçiniz",
                        Toast.LENGTH_SHORT
                    ).show()

                    val alert = AlertDialog.Builder(requireContext())
                    alert.setTitle("Uyarı")
                    alert.setMessage("Fotoğraf Eklemeden Devam Etmek İstiyormusunz?")
                    alert.setPositiveButton(
                        "EVET",
                        DialogInterface.OnClickListener { dialog, which ->
                            postData(view)
                        }).setNegativeButton(
                        "HAYIR",
                        DialogInterface.OnClickListener { dialog, which ->
                            dialog.dismiss()
                        })
                    val dialog = alert.create()
                    dialog.show()
                }

            } else {
                dtTxtDescription.error = "Lütfen Açıklamayı Doldurunuz"
                Toast.makeText(
                    requireContext(),
                    "Lütfen Açıklamayı Doldurunuz",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }

//            viewModel.getList(requireContext())

        imgSelected1.setOnClickListener {
            val alert = AlertDialog.Builder(requireContext())
            alert.setMessage("Fotoğrafı Kaldırmak İstiyormusunuz?")
            alert.setPositiveButton("EVET", DialogInterface.OnClickListener { dialog, which ->
                test.remove(test[0])
                count--
                if (imageList!![2]) {
                    imgSelected1.background = imgSelected2.background
                    imgSelected2.background = imgSelected3.background
                    imgSelected3.visibility = View.INVISIBLE
                    imageList!![2] = false
                } else if (imageList!![1]) {
                    imgSelected1.background = imgSelected2.background
                    imgSelected2.visibility = View.INVISIBLE
                    imageList!![1] = false

                } else {
                    imgSelected1.visibility = View.GONE
                    imageList!![0] = false

                }

            })
            alert.setNegativeButton("HAYIR", DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
            })
            val dialog = alert.create()
            dialog.show()

        }
        imgSelected2.setOnClickListener {

            val alert = AlertDialog.Builder(requireContext())
            alert.setMessage("Fotoğrafı Kaldırmak İstiyormusunuz?")
            alert.setPositiveButton("EVET", DialogInterface.OnClickListener { dialog, which ->
                test.remove(test[1])
                count--
                if (imageList!![2]) {
                    imgSelected2.background = imgSelected3.background
                    imgSelected3.visibility = View.INVISIBLE
                    imageList!![2] = false
                } else {
                    imgSelected2.visibility = View.INVISIBLE
                    imageList!![1] = false

                }


            })
            alert.setNegativeButton("HAYIR", DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
            })
            val dialog = alert.create()
            dialog.show()

        }
        imgSelected3.setOnClickListener {

            val alert = AlertDialog.Builder(requireContext())
            alert.setMessage("Fotoğrafı Kaldırmak İstiyormusunuz?")
            alert.setPositiveButton("EVET", DialogInterface.OnClickListener { dialog, which ->
                imageList!![2] = false
                test.remove(test[2])
                count--
                imgSelected3.visibility = View.INVISIBLE


            })
            alert.setNegativeButton("HAYIR", DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
            })
            val dialog = alert.create()
            dialog.show()

        }

    }

    private fun observeLiveData(view: View) {

        viewModel.nwLoading.observe(viewLifecycleOwner, Observer { response ->
            response?.let {
                if (!it) {
                    llProgressBar.visibility = View.GONE
                    btnNewComplaint.visibility = View.VISIBLE
                    btnSelectPhoto.visibility = View.VISIBLE

                    Navigation.findNavController(view)
                        .navigate(NewComplaintFragmentDirections.actionNewComplaintFragmentToHomeFragment())

                    Toast.makeText(
                        requireContext(),
                        "BAŞVURUNUZ BAŞARIYLA TAMAMLANMIŞTIR",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
        viewModel.nwLoading.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    llProgressBar.visibility = View.GONE
                    btnNewComplaint.visibility = View.VISIBLE
                    btnSelectPhoto.visibility = View.VISIBLE

                } else {
                    llProgressBar.visibility = View.GONE
                    btnNewComplaint.visibility = View.VISIBLE
                    btnSelectPhoto.visibility = View.VISIBLE

                }
            }
        })
        viewModel.error.observe(viewLifecycleOwner, Observer {
            it?.let {
                pbText.text = it.message
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                llProgressBar.visibility = View.GONE
            }
        })
    }

    private fun postData(view: View) {
        val description = dtTxtDescription.text.toString()
        llProgressBar.visibility = View.VISIBLE
        btnNewComplaint.visibility = View.INVISIBLE
        btnSelectPhoto.visibility = View.INVISIBLE
        val categoryDto = CategoryDto(1)
        val complainStatsDto = ComplaintStatusDto(1)
        val userDto = getID(requireContext())?.let { it1 -> UserDto(it1) }
        complaintGallery =
            listOf(
                complaintImage1,
                complaintImage2,
                complaintImage3
            ) as List<ComplaintGallery>


        val newComplaint = userDto?.let { it1 ->
            NewComplaint(
                categoryDto,
                test,
                complainStatsDto,
                description,
                "${currentLatLng.latitude},${currentLatLng.longitude}",
                0,
                it1
            )
        }
        newComplaint?.let {
            viewModel.postComplaint(requireContext(), it)
        }
        observeLiveData(view)
    }

    private fun takePhoto() {
        val imageTakeInten = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (activity?.packageManager?.let { imageTakeInten.resolveActivity(it) } != null) startActivityForResult(
            imageTakeInten,
            REQUEST_IMAGE_CAPTURE
        )

    }

    private fun selectGallery() {
        val selectImage =
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        selectImage.type = "image/"
        startActivityForResult(selectImage, REQUEST_IMAGE_GALLERY)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val extras = data?.extras
            val bitmap: Bitmap = extras?.get("data") as Bitmap
            imageShow(bitmap)


            println(encodeImage(bitmap))
        }


        if (requestCode == REQUEST_IMAGE_GALLERY && resultCode == RESULT_OK) {
            val uri = data?.data
            try {
                val bitmap =
                    MediaStore.Images.Media.getBitmap(activity?.contentResolver, uri)
                imageShow(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }


    private fun imageShow(bitmap: Bitmap) {
        loop@ for (x in 0..2) {
            if (!imageList!![x]) {
                when (x) {

                    0 -> {

                        val ob = BitmapDrawable(resources, bitmap)
                        imgSelected1.background = ob
                        imgSelected1.visibility = View.VISIBLE
                        imageList!![x] = true
                        complaintImage1 = encodeImage(bitmap)?.let {
                            ComplaintGallery(imageUrl = it)
                        }
                        complaintImage1?.let { test.add(it) }
                        count++

                        break@loop
                    }
                    1 -> {
                        val ob = BitmapDrawable(resources, bitmap)
                        imgSelected2.background = ob
                        imgSelected2.visibility = View.VISIBLE
                        imageList!![x] = true
                        complaintImage2 =
                            encodeImage(bitmap)?.let { ComplaintGallery(imageUrl = it) }!!
                        complaintImage2?.let { test.add(it) }
                        count++

                        break@loop
                    }
                    2 -> {
                        val ob = BitmapDrawable(resources, bitmap)
                        imgSelected3.background = ob
                        imgSelected3.visibility = View.VISIBLE
                        imageList!![x] = true
                        complaintImage3 =
                            encodeImage(bitmap)?.let { ComplaintGallery(imageUrl = it) }!!
                        complaintImage3?.let { test.add(it) }
                        count++
                        break@loop
                    }
                }
            }
        }
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
        private const val REQUEST_IMAGE_CAPTURE = 101
        private const val REQUEST_IMAGE_GALLERY = 102
    }

    private fun setUpMap() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
            return
        }
        map.isMyLocationEnabled = true

        fusedLocationClient =
            LocationServices.getFusedLocationProviderClient(requireContext())



        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            // Got last known location. In some rare situations this can be null.
            if (location != null) {
                lastLocation = location
                currentLatLng = LatLng(location.latitude, location.longitude)
                placeMarkerOnMap(currentLatLng)
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15f))
            }

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        if (requestCode == 1) {
            if (grantResults.isNotEmpty()) {
                if (ContextCompat.checkSelfPermission(
                        requireContext(),
                        android.Manifest.permission.ACCESS_FINE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED
                ) {

                    map.isMyLocationEnabled = true


                    fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            lastLocation = location
                            currentLatLng =
                                LatLng(location.latitude, location.longitude)
                            placeMarkerOnMap(currentLatLng)
                            map.animateCamera(
                                CameraUpdateFactory.newLatLngZoom(
                                    currentLatLng,
                                    15f
                                )
                            )
                        }

                    }
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    private fun placeMarkerOnMap(location: LatLng) {
        val markerOptions = MarkerOptions().position(location)
        map.addMarker(markerOptions)
    }

    override fun onMarkerClick(p0: Marker?) = false
    override fun onStart() {
        super.onStart()
        fusedLocationClient =
            LocationServices.getFusedLocationProviderClient(requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient =
            LocationServices.getFusedLocationProviderClient(requireContext())
    }

}