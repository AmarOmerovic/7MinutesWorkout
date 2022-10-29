package com.amaromerovic.a7minutesworkout

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.amaromerovic.a7minutesworkout.adapter.ExerciseRecyclerViewAdapter
import com.amaromerovic.a7minutesworkout.databinding.ActivityExerciseBinding
import com.amaromerovic.a7minutesworkout.databinding.CustomDialogBinding
import com.amaromerovic.a7minutesworkout.model.Exercise
import com.amaromerovic.a7minutesworkout.util.Constants
import com.google.android.flexbox.*
import java.util.*

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private lateinit var binding: ActivityExerciseBinding
    private var restTimer: CountDownTimer? = null
    private var countDownTimeRest: Int = 10000
    private var restProgress: Int = 0

    private var exerciseTimer: CountDownTimer? = null
    private var countDownTimeExercise: Int = 30000
    private var exerciseProgress: Int = 0

    private lateinit var exerciseList: ArrayList<Exercise>
    private var currentExercise = -1

    private var textToSpeech: TextToSpeech? = null
    private lateinit var recyclerViewAdapter: ExerciseRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolBar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (intent.getIntExtra(Constants.SELECTED_WORKOUT, -1) == 1) {
            exerciseList = Constants.workoutA()
        } else if (intent.getIntExtra(Constants.SELECTED_WORKOUT, -1) == 2) {
            exerciseList = Constants.workoutB()
        }
        textToSpeech = TextToSpeech(this, this)
        recyclerViewAdapter = ExerciseRecyclerViewAdapter(exerciseList)

        val layoutManager = FlexboxLayoutManager(applicationContext).apply {
            justifyContent = JustifyContent.CENTER
            alignItems = AlignItems.CENTER
            flexDirection = FlexDirection.ROW
            flexWrap = FlexWrap.WRAP
        }
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = recyclerViewAdapter

        binding.toolBar.setNavigationOnClickListener {

            val customDialog = Dialog(this)
            val dialogBinding = CustomDialogBinding.inflate(layoutInflater)
            customDialog.setContentView(dialogBinding.root)
            customDialog.setCancelable(false)
            dialogBinding.yes.setOnClickListener {
                val intent = Intent(this@ExerciseActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
                customDialog.dismiss()
            }
            dialogBinding.no.setOnClickListener {
                customDialog.dismiss()
            }
            customDialog.show()
        }

        binding.textViewTimer.text = "${countDownTimeRest / 1000}"
        setUpRestView()

    }

    private fun setUpRestView() {
        restTimer?.cancel()
        restProgress = 0
        currentExercise++
        val nextExercise: String = exerciseList[currentExercise].getName()
        binding.upComingExercise.text = nextExercise
        Handler.createAsync(Looper.getMainLooper()).postDelayed({
            speakText(binding.upcomingText.text.toString().trim())
            speakText(nextExercise)

        }, 1000)
        startRestTimer()
    }

    private fun startRestTimer() {
        binding.progressBar.progress = restProgress

        restTimer = object : CountDownTimer((countDownTimeRest - restProgress).toLong(), 1000) {
            override fun onTick(p0: Long) {
                binding.textViewTimer.text = (p0 / 1000).toString()
                restProgress++
                binding.progressBar.progress = Constants.PROGRESS_BAR_MAX_VALUE_REST - restProgress
                if (p0 in 1000..4000) {
                    speakText((p0 / 1000).toString().trim())
                } else if (p0 < 1000) {
                    speakText("Start")
                }
            }

            override fun onFinish() {
                if (binding.title.visibility == View.VISIBLE && binding.frameLayout.visibility == View.VISIBLE && binding.upComingExercise.visibility == View.VISIBLE && binding.upComingExercise.visibility == View.VISIBLE) {
                    binding.title.visibility = View.GONE
                    binding.frameLayout.visibility = View.GONE
                    binding.upcomingText.visibility = View.GONE
                    binding.upComingExercise.visibility = View.GONE
                    binding.recyclerView.visibility = View.GONE
                }


                if (binding.exerciseName.visibility == View.GONE && binding.exerciseFrameLayout.visibility == View.GONE && binding.exerciseImage.visibility == View.GONE && binding.recyclerView.visibility == View.GONE
                ) {
                    binding.exerciseName.visibility = View.VISIBLE
                    binding.exerciseFrameLayout.visibility = View.VISIBLE
                    binding.exerciseImage.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.VISIBLE
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
        exercise.setIsSelected(true)
        recyclerViewAdapter.notifyDataSetChanged()
        val exerciseName: String = exercise.getName()
        binding.exerciseName.text = exerciseName
        speakText(exerciseName.trim())
        binding.exerciseImage.setImageResource(exercise.getImage())

        exerciseTimer =
            object : CountDownTimer((countDownTimeExercise - exerciseProgress).toLong(), 1000) {
                override fun onTick(p0: Long) {
                    binding.textViewExerciseTimer.text = (p0 / 1000).toString()
                    exerciseProgress++
                    binding.exerciseProgressBar.progress =
                        Constants.PROGRESS_BAR_MAX_VALUE_EXERCISE - exerciseProgress
                    if (p0 in 1000..4000) {
                        speakText((p0 / 1000).toString().trim())
                    } else if (p0 < 1000) {
                        speakText("Rest")
                    }
                }

                override fun onFinish() {
                    exercise.setIsCompleted(true)
                    if (binding.exerciseName.visibility == View.VISIBLE && binding.exerciseFrameLayout.visibility == View.VISIBLE && binding.exerciseImage.visibility == View.VISIBLE && binding.recyclerView.visibility == View.VISIBLE) {
                        binding.exerciseName.visibility = View.GONE
                        binding.exerciseFrameLayout.visibility = View.GONE
                        binding.exerciseImage.visibility = View.GONE
                        binding.recyclerView.visibility = View.GONE
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
                        val intent = Intent(this@ExerciseActivity, FinishActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }

            }.start()
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = textToSpeech!!.setLanguage(Locale.ENGLISH)
            if (result == TextToSpeech.LANG_AVAILABLE) {
                Log.e("TextToSpeechErrors", "onInit: Language available")
            } else {
                Log.e("TextToSpeechErrors", "onInit: There was a problem with the language")
            }
        } else {
            Log.e("TextToSpeechErrors", "onInit: Initialization failed.")
        }
    }

    private fun speakText(text: String) {
        textToSpeech!!.speak(text, TextToSpeech.QUEUE_ADD, null, "")
    }

    override fun onDestroy() {
        if (restTimer != null) {
            restTimer?.cancel()
            restProgress = 0
        }
        if (exerciseTimer != null) {
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }

        if (textToSpeech != null) {
            textToSpeech?.stop()
        }
        super.onDestroy()
    }
}