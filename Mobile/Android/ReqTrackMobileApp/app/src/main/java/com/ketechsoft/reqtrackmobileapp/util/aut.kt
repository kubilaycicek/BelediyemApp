package com.ketechsoft.reqtrackmobileapp.util

import android.content.Context


/**     Code with ❤
╔════════════════════════════╗
║  Created by Emre GÖREN ║
╠════════════════════════════╣
║ emregrnn58@gmail.com             ║
╠════════════════════════════╣
║     03/05/2020 - 01:47     ║
╚════════════════════════════╝
 */
fun saveToken(context: Context, token: String) {
    val sharedPreferences = context.getSharedPreferences("PREF", Context.MODE_PRIVATE)
    sharedPreferences.edit().putString("token", token).apply()
}

fun getToken(context: Context): String? {
    val sharedPreferences = context.getSharedPreferences("PREF", Context.MODE_PRIVATE)
    return sharedPreferences.getString("token", null)
}

fun saveID(context: Context, id: Int) {
    val sharedPreferences = context.getSharedPreferences("PREF", Context.MODE_PRIVATE)
    sharedPreferences.edit().putInt("id", id).apply()
}

fun getID(context: Context): Int? {
    val sharedPreferences = context.getSharedPreferences("PREF", Context.MODE_PRIVATE)
    return sharedPreferences.getInt("id", 0)
}

