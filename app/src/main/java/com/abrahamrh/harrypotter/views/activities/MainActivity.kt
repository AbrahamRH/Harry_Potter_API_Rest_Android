package com.abrahamrh.harrypotter.views.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.abrahamrh.harrypotter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun students(view: View) {
        val intent = Intent(this, CharactersList::class.java)
        startActivity(intent)
    }
    fun profesors(view: View) {
        val intent = Intent(this, CharactersList::class.java)
        startActivity(intent)
    }
}