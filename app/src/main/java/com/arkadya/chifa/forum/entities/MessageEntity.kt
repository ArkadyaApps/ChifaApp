package com.arkadya.chifa.forum.entities


import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "messages",
    foreignKeys = [
        ForeignKey(
            entity = ChatRoomEntity::class,
            parentColumns = ["id"], // Correctly referencing the primary key column in ChatRoomEntity
            childColumns = ["chatRoomId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["chatRoomId"])] // Add the index here
)
data class MessageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val chatRoomId: Long,
    val senderId: String,
    val senderName: String,
    val message: String,
    val replyToMessageId: Long? = null,
    val timestamp: Long = System.currentTimeMillis()
)