package com.ketechsoft.reqtrackmobileapp.model

import com.google.gson.annotations.SerializedName


/**     Code with ❤
╔════════════════════════════╗
║  Created by Emre GÖREN ║
╠════════════════════════════╣
║ emregrnn58@gmail.com             ║
╠════════════════════════════╣
║     29/03/2020 - 12:31     ║
╚════════════════════════════╝
 */
data class UserResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("surname")
    val surname: Any,
    @SerializedName("username")
    val username: String,
    @SerializedName("phone")
    val phone: Any,
    @SerializedName("tcNumber")
    val tcNumber: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("address")
    val address: Any
)