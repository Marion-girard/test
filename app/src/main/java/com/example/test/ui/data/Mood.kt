package com.example.test.ui.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "moods")
data class Mood(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val date: String, // Vous pouvez utiliser LocalDate ou un autre type selon vos besoins
    val mood: String
)