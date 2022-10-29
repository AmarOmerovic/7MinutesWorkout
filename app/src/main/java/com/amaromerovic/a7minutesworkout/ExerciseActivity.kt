package com.amaromerovic.a7minutesworkout

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.amaromerovic.a7minutesworkout.databinding.ActivityExerciseBinding
import com.amaromerovic.a7minutesworkout.model.Exercise

class ExerciseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExerciseBinding
    private var restTimer: CountDownTimer? = null
    private var countDownTimeRest: Int = 10000
    private var restProgress: Int = 0

    private var exerciseTimer: CountDownTimer? = null
    private var countDownTimeExercise: Int = 30000
    private var exerciseProgress: Int = 0

    private lateinit var exerciseList: ArrayList<Exercise>
    private var currentExercise = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolBar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        exerciseList = Constants.defaultExerciseList()

        binding.toolBar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.textViewTimer.text = "${countDownTimeRest / 1000}"
        setUpRestView()

    }

    private fun setUpRestView() {
        restTimer?.cancel()
        restProgress = 0
        currentExercise++
        binding.upComingExercise.text = exerciseList[currentExercise].getName()
        startRestTimer()
    }

    private fun startRestTimer() {
        binding.progressBar.progress = restProgress

        restTimer = object : CountDownTimer((countDownTimeRest - restProgress).toLong(), 1000) {
            override fun onTick(p0: Long) {
                binding.textViewTimer.text = (p0 / 1000).toString()
                restProgress++
                binding.progressBar.progress = Constants.PROGRESS_BAR_MAX_VALUE_REST - restProgress
            }

            override fun onFinish() {
                if (binding.title.visibility == View.VISIBLE && binding.frameLayout.visibility == View.VISIBLE && binding.upComingExercise.visibility == View.VISIBLE && binding.upComingExercise.visibility == View.VISIBLE) {
                    binding.title.visibility = View.GONE
                    binding.frameLayout.visibility = View.GONE
                    binding.upcomingText.visibility = View.GONE
                    binding.upComingExercise.visibility = View.GONE
                }


                if (binding.exerciseName.visibility == View.GONE && binding.exerciseFrameLayout.visibility == View.GONE && binding.exerciseImage.visibility == View.GONE
                ) {
                    binding.exerciseName.visibility = View.VISIBLE
                    binding.exerciseFrameLayout.visibility = View.VISIBLE
                    binding.exerciseImage.visibility = View.VISIBLE
                }
                setUpExerciseView()
            }

        }.start()
    }

    private fun setUpExerciseView() {
        exerciseTimer?.cancel()
        exerciseProgress = 0
        startExerciseTimer()
    }

    private fun startExerciseTimer() {
        binding.exerciseProgressBar.progress = exerciseProgress
        val exercise = exerciseList[currentExercise]
        binding.exerciseName.text = exercise.getName()
        binding.exerciseImage.setImageResource(exercise.getImage())

        exerciseTimer =
            object : CountDownTimer((countDownTimeExercise - exerciseProgress).toLong(), 1000) {
                override fun onTick(p0: Long) {
                    binding.textViewExerciseTimer.text = (p0 / 1000).toString()
                    exerciseProgress++
                    binding.exerciseProgressBar.progress =
                        Constants.PROGRESS_BAR_MAX_VALUE_EXERCISE - exerciseProgress
                }

                override fun onFinish() {
                    if (binding.exerciseName.visibility == View.VISIBLE && binding.exerciseFrameLayout.visibility == View.VISIBLE && binding.exerciseImage.visibility == View.VISIBLE) {
                        binding.exerciseName.visibility = View.GONE
                        binding.exerciseFrameLayout.visibility = View.GONE
                        binding.exerciseImage.visibility = View.GONE
                    }

                    if (binding.title.visibility == View.GONE && binding.frameLayout.visibility == View.GONE && binding.upComingExercise.visibility == View.GONE && binding.upcomingText.visibility == View.GONE) {
                        binding.title.visibility = View.VISIBLE
                        binding.frameLayout.visibility = View.VISIBLE
                        binding.upComingExercise.visibility = View.VISIBLE
                        binding.upcomingText.visibility = View.VISIBLE
                    }
                    if (currentExercise != exerciseList.size - 1) {
                        setUpRestView()
                    } else {
                        val intent = Intent(this@ExerciseActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }

            }.start()
    }

    override fun onDestroy() {
        restTimer?.cancel()
        restProgress = 0
        exerciseTimer?.cancel()
        exerciseProgress = 0
        super.onDestroy()
    }
}