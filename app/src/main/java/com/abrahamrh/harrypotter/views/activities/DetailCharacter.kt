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

        Log.d("DEBUG", "request - ${call.request()}")
        call.enqueue(object: Callback<ArrayList<CharacterDetail>> {
            override fun onResponse(
                call: Call<ArrayList<CharacterDetail>>,
                response: Response<ArrayList<CharacterDetail>>
            ) {
                Log.d("DEBUG", "response - ${response.body()}")
                binding.pbConexion.visibility = View.GONE
                val character = response.body()!![0]
                binding.tvDetailName.text = character.name
                binding.tvAncestry.text = character.ancestry
                binding.tvHouse.text = character.house
                binding.tvSpecie.text =  character.species
                binding.tvPatronus.text = character.patronus
                binding.tvDateBirth.text = character.dateOfBirth
                binding.tvWizard.text = if(character.wizard!!){
                    getString(R.string.Si)
                } else {
                    getString(R.string.No)
                }

                Glide.with(this@DetailCharacter)
                    .load(character.image)
                    .into(binding.ivImage)
            }
            override fun onFailure(call: Call<ArrayList<CharacterDetail>>, t: Throwable) {
                Log.d("DEBUG", "Failure")
                Log.d("DEBUG", "Error Cause - ${t.cause}")
                Log.d("DEBUG", "Error Message- ${t.message}")
            }

        })
    }
}