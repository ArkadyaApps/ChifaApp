package com.arkadya.chifa.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arkadya.chifa.data.local.dao.MyDao
import com.arkadya.chifa.data.local.entities.MyEntity

@Database(
    entities = [MyEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun myDao(): MyDao
}