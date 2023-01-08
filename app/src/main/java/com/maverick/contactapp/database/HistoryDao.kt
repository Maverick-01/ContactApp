package com.maverick.contactapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.maverick.contactapp.models.History

@Dao
interface HistoryDao {
    @Insert
    suspend fun insert(history: History)

    @Query("select * from history")
    fun getAllHistory(): LiveData<List<History>>
}