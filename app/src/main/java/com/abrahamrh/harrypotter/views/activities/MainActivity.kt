package com.abrahamrh.harrypotter.views.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.abrahamrh.harrypotter.R
import com.abrahamrh.harrypotter.databinding.ActivityMainBinding
import com.abrahamrh.harrypotter.service.BackgroundSoundService

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isMusicEnable = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startService(Intent(this,BackgroundSoundService::class.java))
    }

    fun students(view: View) {
        val intent = Intent(this, CharactersList::class.java)
        val bundle = Bundle()
        bundle.putString("endpoint","characters/students")
        intent.putExtras(bundle)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }
    fun profesors(view: View) {
        val intent = Intent(this, CharactersList::class.java)
        val bundle = Bundle()
        bundle.putString("endpoint","characters/staff")
        intent.putExtras(bundle)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }

    fun enableMusic(view: View) {
        if(isMusicEnable){
            stopService(Intent(this,BackgroundSoundService::class.java))
            binding.ibtnMusic.setImageResource(R.drawable.music_off)
        } else {
            startService(Intent(this,BackgroundSoundService::class.java))
            binding.ibtnMusic.setImageResource(R.drawable.music_on)
        }
        isMusicEnable = !isMusicEnable
    }
}