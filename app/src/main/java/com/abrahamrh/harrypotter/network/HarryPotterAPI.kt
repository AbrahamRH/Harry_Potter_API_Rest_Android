package com.abrahamrh.harrypotter.network

import com.abrahamrh.harrypotter.model.Character
import com.abrahamrh.harrypotter.model.CharacterDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface HarryPotterAPI {

    @GET
    fun getCharacters(
        @Url url: String?
    ) : Call<ArrayList<Character>>

    @GET("character/{id}")
    fun getCharacter(
        @Path("id") id: String?
    ) : Call<ArrayList<CharacterDetail>>
}