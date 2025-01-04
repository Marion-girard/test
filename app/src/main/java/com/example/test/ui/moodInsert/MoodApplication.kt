package com.example.test.ui.moodInsert

import android.app.Application
import com.example.test.ui.data.AppDatabase

class MoodApplication : Application() {
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}