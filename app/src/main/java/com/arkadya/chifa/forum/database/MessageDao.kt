package com.arkadya.chifa.forum.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.arkadya.chifa.forum.entities.MessageEntity
import kotlinx.coroutines.flow.Flow
import com.arkadya.chifa.data.local.entities.MyEntity

@Dao
interface MessageDao {
    @Insert
    suspend fun insertMessage(message: MessageEntity)

    @Query("SELECT * FROM messages WHERE chatRoomId = :roomId ORDER BY timestamp ASC")
    fun getMessagesByRoom(roomId: Long): Flow<List<MessageEntity>>

    // These methods are for MyEntity and will be implemented later.
    @Suppress("unused")
    @Query("SELECT * FROM my_entity_table")
    fun getAllItems(): Flow<List<MyEntity>>

    @Suppress("unused")
    @Insert
    suspend fun insert(item: MyEntity)
}