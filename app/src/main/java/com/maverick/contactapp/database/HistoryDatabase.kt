package com.maverick.contactapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.maverick.contactapp.models.History

@Database(entities = [History::class], version = 1)
abstract class HistoryDatabase : RoomDatabase() {

    abstract fun HistoryDao(): HistoryDao

    companion object {
        private var instance: HistoryDatabase? = null

        @Synchronized
        fun getInstance(ctx: Context): HistoryDatabase {
            if (instance == null)
                instance = Room.databaseBuilder(
                    ctx.applicationContext, HistoryDatabase::class.java,
                    "note_database"
                )
                    .build()

            return instance!!

        }
    }
}