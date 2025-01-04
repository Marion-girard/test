package com.example.test.ui.moodInsert

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.test.ui.data.Mood


import com.example.test.ui.data.MoodDao
import kotlinx.coroutines.launch

class MoodViewModel(private val itemDao: MoodDao) : ViewModel() {



     fun insertItem(mood: Mood){
        viewModelScope.launch {
            itemDao.insert(mood)
        }
    }

     fun getNewItemEntry(date: String, mood:Int):Mood{
        return Mood (
            date = date,
            mood = mood

        )
    }

    /*fun addNewItem(date: String, mood: String ){
        val newMood =  getNewItemEntry(date, mood)
        insertItem(newMood)
    }*/
    /*fun isEntryValid(date: String, mood: Int): Boolean{
        if (date.isBlank()|| mood.isBlank()){
            return false
        }
        return true
    }*/
}

class MoodViewModelFactory(private val moodDao: MoodDao): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T{
        if (modelClass.isAssignableFrom(MoodViewModel::class.java)){
            @Suppress("UNCHECKED_CAT")
            return MoodViewModel(moodDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}