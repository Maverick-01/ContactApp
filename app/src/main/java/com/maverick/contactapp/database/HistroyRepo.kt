package com.maverick.contactapp.database

import androidx.lifecycle.LiveData
import com.maverick.contactapp.models.History

class HistroyRepo(private val historyDao: HistoryDao) {
    val allHistory : LiveData<List<History>> = historyDao.getAllHistory()

    suspend fun insert(history: History){
        historyDao.insert(history)
    }
}