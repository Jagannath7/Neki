package com.systemtron.neki.db

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface HistoryDao {

    @Insert
    suspend fun insertTask(history: History)
}