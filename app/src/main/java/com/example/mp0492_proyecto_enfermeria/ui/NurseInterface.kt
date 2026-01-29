package com.example.mp0492_proyecto_enfermeria.ui

import com.example.mp0492_proyecto_enfermeria.ui.model.Nurse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface NurseInterface {
    @GET("nurse/index")
    suspend fun getAll():List<Nurse>

    @GET("name")
    suspend fun findByName(@Query("name")name:String): Nurse

    @Headers("Accept: application/json","Content-Type: application/json")
    @POST("new")
    suspend fun createNurse(@Body newNurse: Nurse)

    @GET("{requestedId}")
    suspend fun findById(@Path("requestedId") requestedId: Int): Nurse

    @PUT("{requestedId}")
    suspend fun putNurse(@Path("requestedId") requestedId: Int, @Body nurseUpdate: Nurse)

    @DELETE("{requestedId}")
    suspend fun delete(@Path("requestedId") requestedId: Int)

}