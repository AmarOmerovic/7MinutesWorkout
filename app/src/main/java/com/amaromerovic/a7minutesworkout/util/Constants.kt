package com.amaromerovic.a7minutesworkout.util

import com.amaromerovic.a7minutesworkout.R
import com.amaromerovic.a7minutesworkout.model.Exercise

object Constants {
    const val PROGRESS_BAR_MAX_VALUE_EXERCISE = 30
    const val PROGRESS_BAR_MAX_VALUE_REST = 10

    fun defaultExerciseList(): ArrayList<Exercise> {
        val exercises = ArrayList<Exercise>()
        exercises.add(Exercise(0, "Abdominal crunch", R.drawable.abdominal_crunch, false, false))
        exercises.add(
            Exercise(
                1,
                "High knees running in place",
                R.drawable.high_knees_running_in_place,
                false,
                false
            )
        )
        exercises.add(Exercise(2, "Jumping jacks", R.drawable.jumping_jacks, false, false))
        exercises.add(Exercise(3, "Lunge", R.drawable.lunge, false, false))
        exercises.add(Exercise(4, "Plank", R.drawable.plank, false, false))
        exercises.add(Exercise(5, "Push up", R.drawable.push_up, false, false))
        exercises.add(
            Exercise(
                6,
                "Push up and rotation",
                R.drawable.push_up_and_rotation,
                false,
                false
            )
        )
        exercises.add(Exercise(7, "Side plank", R.drawable.side_plank, false, false))
        exercises.add(Exercise(8, "Squat", R.drawable.squat, false, false))
        exercises.add(
            Exercise(
                9,
                "Step up onto chair",
                R.drawable.step_up_onto_chair,
                false,
                false
            )
        )
        exercises.add(
            Exercise(
                10,
                "Triceps dip on chair",
                R.drawable.triceps_dip_on_chair,
                false,
                false
            )
        )
        exercises.add(Exercise(11, "Wall sit", R.drawable.wall_sit, false, false))
        return exercises
    }
}