package com.example.mp0492_proyecto_enfermeria.ui

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RemoteConnection {
    private val conn =  Retrofit.Builder()
    .baseUrl("http://10.0.2.2:8080/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

    val endPoints: NurseInterface = conn.create(NurseInterface::class.java)
}