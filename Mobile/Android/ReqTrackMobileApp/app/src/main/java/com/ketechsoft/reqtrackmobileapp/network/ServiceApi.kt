package com.ketechsoft.reqtrackmobileapp.network

import com.ketechsoft.reqtrackmobileapp.model.*
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.*


/**     Code with ❤
╔════════════════════════════╗
║  Created by Emre GÖREN ║
╠════════════════════════════╣
║ emregrnn58@gmail.com             ║
╠════════════════════════════╣
║     29/03/2020 - 12:28     ║
╚════════════════════════════╝
 */
interface ServiceApi {

    @GET("/user/list")
    fun getData(@Header("Authorization") token: String): Observable<List<UserResponse>>

    @Headers("Content-Type: application/json")
    @POST("/api/token/login")
    fun login(@Body login: Login): Single<LoginResponse>

    @POST("/api/token/register")
    fun register(@Body register: Register): Single<RegisterResponse>

    @POST("/complaint/")
    fun newComplaint(
        @Header("Authorization") token: String,
        @Body newComplaint: NewComplaint
    ): Single<NewComplaint>


    @GET("complaint/list/user/{id}")
    fun complaint(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Observable<Complaint>

    @GET("user/{id}")
    fun profile(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Observable<Profile>
}