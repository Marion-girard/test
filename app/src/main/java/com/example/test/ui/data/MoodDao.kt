package com.example.test.ui.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MoodDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(mood: Mood)

    @Query("SELECT * FROM mood WHERE date = :date")
    suspend fun getMoodsByDate(date: String): List<Mood>

    @Query("SELECT * FROM mood") //interval de date à faire
    suspend fun getAllMoods(): List<Mood>
}