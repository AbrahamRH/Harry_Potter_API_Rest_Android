package com.abrahamrh.harrypotter.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.abrahamrh.harrypotter.databinding.ActivityCharactersListBinding
import com.abrahamrh.harrypotter.model.Character
import com.abrahamrh.harrypotter.network.HarryPotterAPI
import com.abrahamrh.harrypotter.network.RetrofitService
import com.abrahamrh.harrypotter.views.adapters.CharactersAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharactersList : AppCompatActivity() {

    private lateinit var binding: ActivityCharactersListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        if (bundle != null){
            val call = RetrofitService.getRetrofit()
                .create(HarryPotterAPI::class.java)
                .getCharacters(bundle.getString("endpoint"))

            call.enqueue(object: Callback<ArrayList<Character>>{
                override fun onResponse(
                    call: Call<ArrayList<Character>>,
                    response: Response<ArrayList<Character>>
                ) {
                    binding.pbConexion.visibility = View.GONE
                    binding.rvMenu.layoutManager = LinearLayoutManager(this@CharactersList)
                    binding.rvMenu.adapter = CharactersAdapter(this@CharactersList, response.body()!!,)
                }

                override fun onFailure(call: Call<ArrayList<Character>>, t: Throwable) {
                    binding.pbConexion.visibility = View.GONE
                }

            })
        }
    }
}