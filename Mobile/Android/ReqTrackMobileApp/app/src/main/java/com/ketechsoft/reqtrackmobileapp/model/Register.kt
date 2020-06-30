package com.ketechsoft.reqtrackmobileapp.model


/**     Code with ❤
╔════════════════════════════╗
║  Created by Emre GÖREN ║
╠════════════════════════════╣
║ emregrnn58@gmail.com             ║
╠════════════════════════════╣
║     03/05/2020 - 17:35     ║
╚════════════════════════════╝
 */
data class Register(
    private val name: String,
    private val surname: String,
    private val tcNumber: String,
    private val password: String,
    private val email: String,
    private val phone: String
)

data class RegisterResponse(
    val message: String,
    val success: Boolean
)