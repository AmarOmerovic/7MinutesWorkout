package com.amaromerovic.a7minutesworkout.util

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.amaromerovic.a7minutesworkout.data.WorkoutDAO
import com.amaromerovic.a7minutesworkout.model.WorkoutEntity

@Database(entities = [WorkoutEntity::class], version = 1, exportSchema = false)
abstract class WorkoutRoomDatabase : RoomDatabase() {
    abstract fun workoutDAO(): WorkoutDAO

    companion object {

        @Volatile
        private var INSTANCE: WorkoutRoomDatabase? = null

        fun getInstance(context: Context): WorkoutRoomDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        WorkoutRoomDatabase::class.java,
                        "workout history table"
                    ).fallbackToDestructiveMigration().build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}