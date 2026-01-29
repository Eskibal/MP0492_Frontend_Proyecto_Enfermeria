package com.example.mp0492_proyecto_enfermeria.ui

import com.example.mp0492_proyecto_enfermeria.ui.model.Nurse
import retrofit2.Response
import retrofit2.http.*

interface NurseInterface {
    // =========================
    // GET ALL NURSES
    // GET /nurse/index
    // =========================
    @GET("nurse/index")
    suspend fun getAll():List<Nurse>

    // =========================
    // LOGIN
    // POST /nurse/login
    // =========================
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    @POST("nurse/login")
    suspend fun login(
        @Body loginRequest: Nurse
    ): Response<Boolean>

    // =========================
    // FIND BY NAME
    // GET /nurse/name?name=Maria
    // =========================
    @GET("nurse/name")
    suspend fun findByName(
        @Query("name") name: String
    ): Response<Nurse>

    // =========================
    // CREATE NURSE
    // POST /nurse/new
    // =========================
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    @POST("nurse/new")
    suspend fun createNurse(
        @Body newNurse: Nurse
    ): Response<Void>

    // =========================
    // FIND BY ID
    // GET /nurse/{requestedId}
    // =========================
    @GET("nurse/{requestedId}")
    suspend fun findById(
        @Path("requestedId") requestedId: Int
    ): Response<Nurse>

    // =========================
    // UPDATE NURSE
    // PUT /nurse/{requestedId}
    // =========================
    @PUT("nurse/{requestedId}")
    suspend fun putNurse(
        @Path("requestedId") requestedId: Int,
        @Body nurseUpdate: Nurse
    ): Response<Void>

    // =========================
    // DELETE NURSE
    // DELETE /nurse/{requestedId}
    // =========================
    @DELETE("nurse/{requestedId}")
    suspend fun delete(
        @Path("requestedId") requestedId: Int
    ): Response<Void>
}
