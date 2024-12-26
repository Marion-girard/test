package com.example.test.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class HomeViewModel : ViewModel() {

    // Format pour afficher la date
    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")

    // Initialisez _text avec la date d'aujourd'hui
    private val _text = MutableLiveData<String>().apply {
        value = LocalDate.now().format(formatter) // Date d'aujourd'hui
    }

    // Exposez _text en tant que LiveData
    val text: LiveData<String> = _text

    // Méthode pour mettre à jour la date
    fun updateDate(newDate: String) {
        _text.value = newDate
    }
}