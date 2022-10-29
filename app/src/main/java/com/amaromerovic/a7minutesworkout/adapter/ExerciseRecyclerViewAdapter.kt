package com.amaromerovic.a7minutesworkout.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amaromerovic.a7minutesworkout.databinding.ItemExerciseStatusBinding
import com.amaromerovic.a7minutesworkout.model.Exercise

class ExerciseRecyclerViewAdapter(private val exercises: List<Exercise>) :
    RecyclerView.Adapter<ExerciseRecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemExerciseStatusBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val exercise = exercises[position]
        holder.attachItem(exercise)
    }

    override fun getItemCount(): Int {
        return exercises.size
    }

    inner class ViewHolder(val binding: ItemExerciseStatusBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun attachItem(exercise: Exercise) {
                binding.textViewItem.text = String.format("${(exercise.getId() + 1)}")
                if (exercise.getIsCompleted()) {
                    binding.textViewItem.setBackgroundResource(com.amaromerovic.a7minutesworkout.R.drawable.recycler_view_progress_completed)
                } else if (exercise.getIsSelected()) {
                    binding.textViewItem.setBackgroundResource(com.amaromerovic.a7minutesworkout.R.drawable.recycler_view_progress_reached)
                }
            }

    }
}