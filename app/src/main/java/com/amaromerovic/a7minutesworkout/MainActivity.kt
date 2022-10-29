package com.amaromerovic.a7minutesworkout

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.amaromerovic.a7minutesworkout.databinding.ActivityMainBinding
import com.amaromerovic.a7minutesworkout.util.Constants

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var player: MediaPlayer? = null
    private var click = 0
    private var isSelected = false
    private var selected = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        try {
            val sound =
                Uri.parse("android.resource://com.amaromerovic.a7minutesworkout/" + R.raw.theme)
            player = MediaPlayer.create(applicationContext, sound)
            player?.isLooping = true
            player?.setVolume(0.3f, 0.3f)
            player?.start()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }

        binding.start.setOnClickListener {
            player?.stop()
            val intent = Intent(this, ExerciseActivity::class.java)
            intent.putExtra(Constants.SELECTED_WORKOUT, selected)
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

        binding.bmi.setOnClickListener {
            player?.stop()
            val intent = Intent(this@MainActivity, BmiActivity::class.java)
            startActivity(intent)
        }

        binding.history.setOnClickListener {
            player?.stop()
            val intent = Intent(this@MainActivity, HistoryActivity::class.java)
            startActivity(intent)
        }

        binding.workoutA.setOnClickListener {
            if (!isSelected) {
                binding.workoutB.backgroundTintList =
                    ContextCompat.getColorStateList(this@MainActivity, R.color.red)
                binding.workoutBText.setTextColor(
                    ContextCompat.getColor(
                        this@MainActivity,
                        R.color.blue
                    )
                )
                isSelected = true
            }
            binding.workoutA.backgroundTintList =
                ContextCompat.getColorStateList(this@MainActivity, R.color.blue)
            binding.workoutAText.setTextColor(
                ContextCompat.getColor(
                    this@MainActivity,
                    R.color.red
                )
            )

            if (!binding.start.isEnabled) {
                binding.start.isEnabled = true
            }

            selected = 1
        }

        binding.workoutB.setOnClickListener {
            if (isSelected) {
                binding.workoutA.backgroundTintList =
                    ContextCompat.getColorStateList(this@MainActivity, R.color.red)
                binding.workoutAText.setTextColor(
                    ContextCompat.getColor(
                        this@MainActivity,
                        R.color.blue
                    )
                )
                isSelected = false
            }

            binding.workoutB.backgroundTintList =
                ContextCompat.getColorStateList(this@MainActivity, R.color.blue)
            binding.workoutBText.setTextColor(
                ContextCompat.getColor(
                    this@MainActivity,
                    R.color.red
                )
            )

            if (!binding.start.isEnabled) {
                binding.start.isEnabled = true
            }

            selected = 2
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

    override fun onPause() {
        player?.stop()
        super.onPause()
    }

}