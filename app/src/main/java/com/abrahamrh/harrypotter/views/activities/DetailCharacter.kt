package com.abrahamrh.harrypotter.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.abrahamrh.harrypotter.R
import com.abrahamrh.harrypotter.databinding.ActivityDetailCharacterBinding
import com.abrahamrh.harrypotter.model.CharacterDetail
import com.abrahamrh.harrypotter.network.HarryPotterAPI
import com.abrahamrh.harrypotter.network.RetrofitService
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailCharacter : AppCompatActivity() {

    private lateinit var binding: ActivityDetailCharacterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val bundle = intent.extras
        val id = bundle?.getString("id", "")

        val call = RetrofitService.getRetrofit()
            .create(HarryPotterAPI::class.java)
            .getCharacter(id)

        call.enqueue(object: Callback<ArrayList<CharacterDetail>> {
            override fun onResponse(
                call: Call<ArrayList<CharacterDetail>>,
                response: Response<ArrayList<CharacterDetail>>
            ) {
                binding.pbConexion.visibility = View.GONE
                val character = response.body()!![0]
                binding.tvDetailName.text =  if(character.name.equals("")){
                    getString(R.string.notFound)
                } else {
                    character.name
                }
                binding.tvAncestry.text =  if(character.ancestry.equals("")){
                    getString(R.string.notFound)
                } else {
                    character.ancestry
                }
                binding.tvHouse.text =  if(character.house.equals("")){
                    getString(R.string.notFound)
                } else {
                    character.house
                }
                binding.tvSpecie.text =  if(character.species.equals("")){
                    getString(R.string.notFound)
                } else {
                    character.species
                }
                binding.tvPatronus.text = if(character.patronus.equals("")) {
                    getString(R.string.notFound)
                } else {
                    character.patronus
                }
                Log.d("DEBUG","fecha - ${character.dateOfBirth}")
                binding.tvDateBirth.text = if(character.dateOfBirth == null) {
                    getString(R.string.notFound)
                } else {
                    character.dateOfBirth
                }
                binding.tvWizard.text = if(character.wizard!!){
                    getString(R.string.Si)
                } else {
                    getString(R.string.No)
                }

                if(character.image == ""){
                    Glide.with(this@DetailCharacter)
                        .load(R.drawable.character_icon)
                        .into(binding.ivImage)
                } else {
                    Glide.with(this@DetailCharacter)
                        .load(character.image)
                        .into(binding.ivImage)
                }
            }
            override fun onFailure(call: Call<ArrayList<CharacterDetail>>, t: Throwable) {
                Log.d("DEBUG", "Failure")
                Log.d("DEBUG", "Error Cause - ${t.cause}")
                Log.d("DEBUG", "Error Message- ${t.message}")
            }

        })
    }
}