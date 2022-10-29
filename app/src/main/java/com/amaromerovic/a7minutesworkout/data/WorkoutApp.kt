package com.amaromerovic.a7minutesworkout.data

import android.app.Application
import com.amaromerovic.a7minutesworkout.util.WorkoutRoomDatabase

class WorkoutApp: Application() {
    val database by lazy {
        WorkoutRoomDatabase.getInstance(this)
    }
}