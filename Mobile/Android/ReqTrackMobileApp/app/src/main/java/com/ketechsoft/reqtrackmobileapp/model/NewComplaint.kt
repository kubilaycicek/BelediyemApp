package com.ketechsoft.reqtrackmobileapp.model

import com.google.gson.annotations.SerializedName


/**     Code with ❤
╔════════════════════════════╗
║  Created by Emre GÖREN ║
╠════════════════════════════╣
║ emregrnn58@gmail.com             ║
╠════════════════════════════╣
║     13/05/2020 - 23:38     ║
╚════════════════════════════╝
 */
data class NewComplaint(
    @SerializedName("categoryDto")
    val categoryDto: CategoryDto,
    @SerializedName("complaintGalleries")
    val complaintGalleries: List<ComplaintGallery>? = null,
    @SerializedName("complaintStatusDto")
    val complaintStatusDto: ComplaintStatusDto,
    @SerializedName("description")
    val description: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("userDto")
    val userDto: UserDto
)

data class CategoryDto(
    @SerializedName("id")
    val id: Int
)

data class ComplaintGallery(
    @SerializedName("imageUrl")
    val imageUrl: String
)

data class ComplaintStatusDto(
    @SerializedName("id")
    val id: Int
)

data class UserDto(
    @SerializedName("id")
    val id: Int
)