package com.ketechsoft.reqtrackmobileapp.util

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.ConnectivityManager
import android.util.Base64
import android.util.Patterns
import android.widget.ImageView
import java.io.ByteArrayOutputStream


/**     Code with ❤
╔════════════════════════════╗
║  Created by Emre GÖREN ║
╠════════════════════════════╣
║ emregrnn58@gmail.com             ║
╠════════════════════════════╣
║     13/05/2020 - 23:12     ║
╚════════════════════════════╝
 */

fun encodeImage(bm: Bitmap): String? {
    val baos = ByteArrayOutputStream()
    bm.compress(Bitmap.CompressFormat.JPEG, 100, baos)
    val b = baos.toByteArray()
    return Base64.encodeToString(b, Base64.DEFAULT)
}

fun decodeImage(bm: String, imageView: ImageView, resources: Resources) {
    val imageBytes = Base64.decode(bm, Base64.DEFAULT)
    val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    val ob = BitmapDrawable(resources, decodedImage)
    imageView.background = ob
}

fun isEmailValid(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.isNotEmpty()
}

fun isPhoneValid(phone: String): Boolean {
    return Patterns.PHONE.matcher(phone).matches()
}

fun isOnline(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val netInfo = connectivityManager.activeNetworkInfo
    return netInfo != null && netInfo.isConnected
}