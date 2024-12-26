package com.example.test.ui.calendar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.ui.data.AppDatabase
import com.example.test.ui.data.Mood
import kotlinx.coroutines.launch

class CalendarViewModel(private val database: AppDatabase) : ViewModel() {

    // LiveData pour observer les humeurs par date
    private val _moodsForDate = MutableLiveData<List<Mood>>()
    val moodsForDate: LiveData<List<Mood>> get() = _moodsForDate

    // Fonction pour ajouter une humeur
    fun addMood(date: String, mood: String) {
        val newMood = Mood(date = date, mood = mood)
        viewModelScope.launch {
            database.moodDao().insert(newMood)
            // Rechargez les humeurs après l'ajout
            loadMoodsForDate(date)
        }
    }

    // Fonction pour charger les humeurs pour une date spécifique
    fun loadMoodsForDate(date: String) {
        viewModelScope.launch {
            val moods = database.moodDao().getMoodsByDate(date)
            _moodsForDate.postValue(moods) // Met à jour LiveData
        }
    }
}