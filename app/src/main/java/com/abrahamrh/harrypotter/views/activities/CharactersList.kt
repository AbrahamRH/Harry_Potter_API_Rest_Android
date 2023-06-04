package com.abrahamrh.harrypotter.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.abrahamrh.harrypotter.databinding.ActivityCharactersListBinding

class CharactersList : AppCompatActivity() {

    private lateinit var binding: ActivityCharactersListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}