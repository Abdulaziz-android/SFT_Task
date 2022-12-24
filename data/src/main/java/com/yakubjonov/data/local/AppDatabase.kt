package com.yakubjonov.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yakubjonov.data.local.dao.CardDao
import com.yakubjonov.data.local.entity.CardEntity

@Database(entities = [CardEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cardDao(): CardDao

    companion object {
        const val DATABASE = "sft_task_database"
    }
}