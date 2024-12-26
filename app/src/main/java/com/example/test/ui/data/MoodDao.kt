package com.example.test.ui.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MoodDao {
    @Insert
    suspend fun insert(mood: Mood)

    @Query("SELECT * FROM moods WHERE date = :date")
    suspend fun getMoodsByDate(date: String): List<Mood>

    @Query("SELECT * FROM moods")
    suspend fun getAllMoods(): List<Mood>
}