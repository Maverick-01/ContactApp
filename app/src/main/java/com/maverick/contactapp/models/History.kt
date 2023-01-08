package com.maverick.contactapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class History(
    val name: String,
    val sms: String,
    val date: String,
    @PrimaryKey(autoGenerate = false) val id: Int? = null
)