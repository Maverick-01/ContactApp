package com.maverick.contactapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.maverick.contactapp.database.HistoryDatabase
import com.maverick.contactapp.database.HistroyRepo
import com.maverick.contactapp.models.History
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryViewModel(application: Application) : AndroidViewModel(application) {

    val allHistory: LiveData<List<History>>
    val histroyRepo:HistroyRepo

    init {
        val dao = HistoryDatabase.getInstance(application).HistoryDao()
        histroyRepo = HistroyRepo(dao)
        allHistory = histroyRepo.allHistory
    }

    fun addHistory(history: History) = viewModelScope.launch(Dispatchers.IO) {
        histroyRepo.insert(history)
    }
}