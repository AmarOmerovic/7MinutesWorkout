package com.amaromerovic.a7minutesworkout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.amaromerovic.a7minutesworkout.adapter.WorkoutRecyclerViewAdapter
import com.amaromerovic.a7minutesworkout.data.WorkoutApp
import com.amaromerovic.a7minutesworkout.databinding.ActivityHistoryBinding
import kotlinx.coroutines.launch

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolBar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val workoutDAO = (application as WorkoutApp).database.workoutDAO()

        lifecycleScope.launch {
            workoutDAO.allWorkouts().collect {
                val adapter = WorkoutRecyclerViewAdapter(it, this@HistoryActivity)
                binding.recyclerView.layoutManager = LinearLayoutManager(this@HistoryActivity)
                binding.recyclerView.adapter = adapter
            }
        }

    }
}