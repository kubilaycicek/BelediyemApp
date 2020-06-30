package com.ketechsoft.reqtrackmobileapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**     Code with ❤
╔════════════════════════════╗
║  Created by Emre GÖREN ║
╠════════════════════════════╣
║ emregrnn58@gmail.com             ║
╠════════════════════════════╣
║     29/03/2020 - 18:56     ║
╚════════════════════════════╝
 */
data class Login(
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String
)

data class LoginResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("username")
    @Expose
    val username: String,
    @SerializedName("token")
    @Expose
    val token: String
)