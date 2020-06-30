package com.ketechsoft.reqtrackmobileapp.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**     Code with ❤
╔════════════════════════════╗
║  Created by Emre GÖREN ║
╠════════════════════════════╣
║ emregrnn58@gmail.com             ║
╠════════════════════════════╣
║     29/03/2020 - 12:23     ║
╚════════════════════════════╝
 */

private const val BASE_URL = "http://192.168.1.109:8095/"
//private const val BASE_URL = "https://reqtrack.herokuapp.com/"

class Service {


    val retrofit: ServiceApi =
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
            .create(
                ServiceApi::
                class.java
            )


}


