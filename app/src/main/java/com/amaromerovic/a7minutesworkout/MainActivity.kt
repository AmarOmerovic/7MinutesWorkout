package com.amaromerovic.a7minutesworkout

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.amaromerovic.a7minutesworkout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var player: MediaPlayer? = null
    private var click = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        try {
            val sound =
                Uri.parse("android.resource://com.amaromerovic.a7minutesworkout/" + R.raw.theme)
            player = MediaPlayer.create(applicationContext, sound)
            player?.isLooping = true
            player?.setVolume(0.1f, 0.1f)
            player?.start()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }

        binding.start.setOnClickListener {
            player?.stop()
            val intent = Intent(this, ExerciseActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.mute.setOnClickListener {
            click++
            if (click % 2 == 0) {
                player?.setVolume(0.1f, 0.1f)
                binding.mute.setBackgroundResource(R.drawable.mute_button)
            } else {
                player?.setVolume(0f, 0f)
                binding.mute.setBackgroundResource(R.drawable.mute_button_pressed)
            }
        }
    }

    override fun onResume() {
        if (player?.isPlaying == false) {
            player?.prepare()
            player?.start()
        }
        super.onResume()
    }

    override fun onDestroy() {
        if (player != null) {
            player = null
        }
        super.onDestroy()
    }

}