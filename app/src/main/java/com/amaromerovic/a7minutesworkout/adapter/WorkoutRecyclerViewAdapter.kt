package com.amaromerovic.a7minutesworkout.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.amaromerovic.a7minutesworkout.R
import com.amaromerovic.a7minutesworkout.databinding.HistoryRowBinding
import com.amaromerovic.a7minutesworkout.model.WorkoutEntity
import com.amaromerovic.a7minutesworkout.util.Constants

class WorkoutRecyclerViewAdapter(private val workouts: List<WorkoutEntity>,val context: Context) :
    RecyclerView.Adapter<WorkoutRecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            HistoryRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val workout = workouts[position]
        holder.binding.position.text = String.format("${position + 1}")
        holder.binding.date.text = Constants.convertLongToDate(workout.date)
        holder.binding.time.text = Constants.convertLongToTime(workout.date)
        if ((position + 1) % 2 != 0) {
            holder.binding.layout.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.lightGray
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return workouts.size
    }

    class ViewHolder(val binding: HistoryRowBinding) : RecyclerView.ViewHolder(binding.root)
}