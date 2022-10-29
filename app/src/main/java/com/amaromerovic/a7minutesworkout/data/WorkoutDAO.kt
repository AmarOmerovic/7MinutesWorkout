package com.amaromerovic.a7minutesworkout.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.amaromerovic.a7minutesworkout.model.WorkoutEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDAO {
    @Query("SELECT * FROM `workout history table`")
    fun allWorkouts(): Flow<List<WorkoutEntity>>

    @Insert
    fun saveWorkout(workoutEntity: WorkoutEntity)
}