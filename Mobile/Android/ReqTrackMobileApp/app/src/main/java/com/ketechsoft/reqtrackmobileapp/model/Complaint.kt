package com.ketechsoft.reqtrackmobileapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue


class Complaint : ArrayList<ComplaintItem>()

@Parcelize
data class ComplaintItem(
    val category: Category,
    val complaintGalleries: List<ComplaintGaller>,
    val complaintStatus: ComplaintStatus,
    val description: String,
    val id: Int,
    val location: String,
    val user: User
) : Parcelable

@Parcelize
data class Category(
    val description: String,
    val id: Int,
    val name: String
) : Parcelable

@Parcelize
data class ComplaintGaller(
    val id: Int,
    val imageUrl: String
) : Parcelable

@Parcelize
data class ComplaintStatus(
    val description: String,
    val id: Int,
    val name: String
) : Parcelable

@Parcelize
data class User(
    val address: String,
    val email: String,
    val id: Int,
    val name: String,
    val password: String,
    val phone: String,
    val surname: String,
    val tcNumber: String,
    val userType: @RawValue Any
) : Parcelable