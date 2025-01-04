package com.example.test.ui.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mood")
data class Mood(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: String, // Vous pouvez utiliser LocalDate ou un autre type selon vos besoins
    val mood: Int
)