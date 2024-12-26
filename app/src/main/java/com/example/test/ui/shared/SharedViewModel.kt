package com.example.test.ui.shared

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _smiley = MutableLiveData<String>()
    val smiley: LiveData<String> get() = _smiley

    private val _calendarEvent = MutableLiveData<Pair<String, Int>>() // Pair contenant la date et le drawable de l'émoticône
    val calendarEvent: LiveData<Pair<String, Int>> get() = _calendarEvent
    private val _currentDate = MutableLiveData<String>()
    val currentDate: LiveData<String> get() = _currentDate
    fun updateSmiley(smiley: String) {
        _smiley.value = smiley
    }

    fun updateCalendarEvent(date: String, emojiResId: Int) {
        _calendarEvent.value = Pair(date, emojiResId)
    }

}