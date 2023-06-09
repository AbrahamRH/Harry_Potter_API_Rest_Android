package com.abrahamrh.harrypotter.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import com.abrahamrh.harrypotter.R

class BackgroundSoundService : Service() {

    internal lateinit var mp: MediaPlayer

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        mp = MediaPlayer.create(this, R.raw.hpmusic)
        mp.start()
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        mp.stop()
    }
}