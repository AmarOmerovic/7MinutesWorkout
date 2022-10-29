package com.amaromerovic.a7minutesworkout

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.amaromerovic.a7minutesworkout.data.WorkoutApp
import com.amaromerovic.a7minutesworkout.data.WorkoutDAO
import com.amaromerovic.a7minutesworkout.databinding.ActivityFinishBinding
import com.amaromerovic.a7minutesworkout.model.WorkoutEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class FinishActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFinishBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val workoutDAO = (application as WorkoutApp).database.workoutDAO()

        saveWorkout(workoutDAO)


        binding.finish.setOnClickListener {
            val intent = Intent(this@FinishActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


    fun saveWorkout(workoutDAO: WorkoutDAO){
        CoroutineScope(Dispatchers.IO).launch {
            val dateTime: Date = Calendar.getInstance().time
            val dateTimeAsLong: Long = dateTime.time
            workoutDAO.saveWorkout(WorkoutEntity(date = dateTimeAsLong))
        }
    }
}