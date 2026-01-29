package com.example.mp0492_proyecto_enfermeria.ui

import com.example.mp0492_proyecto_enfermeria.ui.model.Nurse
import retrofit2.Response
import retrofit2.http.*

interface NurseInterface {
    @GET("nurse/index")
    suspend fun getAll():List<Nurse>

    @GET("nurse/name")
    suspend fun findByName(@Query("name") name: String): Response<Nurse>

    @GET("{requestedId}")
    suspend fun findById(@Path("requestedId") requestedId: Int): Nurse

    @PUT("{requestedId}")
    suspend fun putNurse(@Path("requestedId") requestedId: Int, @Body nurseUpdate: Nurse)

    @DELETE("{requestedId}")
    suspend fun delete(@Path("requestedId") requestedId: Int)

}
