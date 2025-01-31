package com.arkadya.chifa.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arkadya.chifa.forum.database.MessageDao
import com.arkadya.chifa.data.local.entities.MyEntity
import com.arkadya.chifa.forum.entities.ChatRoomEntity
import com.arkadya.chifa.forum.entities.MessageEntity

@Database(
    entities = [MyEntity::class, MessageEntity::class, ChatRoomEntity::class],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun messageDao(): MessageDao

    companion object {
        const val DATABASE_NAME = "app_database"
    }
}