package com.abrahamrh.harrypotter.network

import retrofit2.http.GET
import retrofit2.http.Path

interface HarryPotterAPI {

    @GET("characters/students")
    fun getStudents()

    @GET("characters/staff")
    fun getStaff()

    @GET("character/{id}")
    fun getCharacter(
        @Path("id") id: String?
    )

}