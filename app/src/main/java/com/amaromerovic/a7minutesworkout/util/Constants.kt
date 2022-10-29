package com.amaromerovic.a7minutesworkout.util

import com.amaromerovic.a7minutesworkout.R
import com.amaromerovic.a7minutesworkout.model.Exercise
import java.text.SimpleDateFormat
import java.util.*

object Constants {
    const val SELECTED_WORKOUT = "selected workout"
    const val PROGRESS_BAR_MAX_VALUE_EXERCISE = 30
    const val PROGRESS_BAR_MAX_VALUE_REST = 10
    const val POUND_VALUE = 0.45359237

    fun workoutA(): ArrayList<Exercise> {
        val exercises = ArrayList<Exercise>()
        exercises.add(Exercise(0, "Back leg", R.drawable.back_leg, false, false))
        exercises.add(
            Exercise(
                1,
                "Cobra lat pull down",
                R.drawable.cobra_lat_pull_down,
                false,
                false
            )
        )
        exercises.add(
            Exercise(
                2,
                "Cross body mountain climbers",
                R.drawable.cross_body_mountain_climbers,
                false,
                false
            )
        )
        exercises.add(Exercise(3, "Good morning", R.drawable.good_morning, false, false))
        exercises.add(Exercise(4, "Push up", R.drawable.push_up, false, false))
        exercises.add(Exercise(5, "Scissor kicks", R.drawable.scissor_kicks, false, false))
        exercises.add(
            Exercise(
                6,
                "Side lunge to curtsy lunge",
                R.drawable.side_lunge_to_curtsy_lunge,
                false,
                false
            )
        )
        exercises.add(Exercise(7, "Sit ups", R.drawable.sit_up, false, false))
        exercises.add(Exercise(8, "Squat jacks", R.drawable.squat_jacks, false, false))
        exercises.add(
            Exercise(
                9,
                "Step up with knee raise",
                R.drawable.step_up_with_knee_raise,
                false,
                false
            )
        )
        exercises.add(
            Exercise(
                10,
                "Touch and hop",
                R.drawable.touch_and_hop,
                false,
                false
            )
        )
        exercises.add(Exercise(11, "Triceps dips", R.drawable.triceps_dips, false, false))
        return exercises
    }




    fun workoutB(): ArrayList<Exercise> {
        val exercises = ArrayList<Exercise>()
        exercises.add(Exercise(0, "Chair dips", R.drawable.chari_dip, false, false))
        exercises.add(
            Exercise(
                1,
                "Jumping jacks",
                R.drawable.jumping_jacks,
                false,
                false
            )
        )
        exercises.add(
            Exercise(
                2,
                "Lunges",
                R.drawable.lunges,
                false,
                false
            )
        )
        exercises.add(Exercise(3, "Plank", R.drawable.plank, false, false))
        exercises.add(Exercise(4, "Push ups", R.drawable.push_ups, false, false))
        exercises.add(Exercise(5, "Running in place", R.drawable.running_in_place, false, false))
        exercises.add(
            Exercise(
                6,
                "Sit up and hold",
                R.drawable.sit_up_and_hold,
                false,
                false
            )
        )
        exercises.add(Exercise(7, "Push up and rotate", R.drawable.push_up_and_rotate, false, false))
        exercises.add(Exercise(8, "Side plank", R.drawable.side_plank, false, false))
        exercises.add(
            Exercise(
                9,
                "Sit up and hold",
                R.drawable.sit_up_and_hold,
                false,
                false
            )
        )
        exercises.add(
            Exercise(
                10,
                "Step up onto chair",
                R.drawable.step_up_onto_chair,
                false,
                false
            )
        )
        exercises.add(Exercise(11, "Wall sit", R.drawable.wall_sit, false, false))
        return exercises
    }


    fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("HH:mm", Locale.US)
        return format.format(date)
    }

    fun convertLongToDate(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("EEEE, dd MMM yyyy", Locale.US)
        return format.format(date)
    }
}