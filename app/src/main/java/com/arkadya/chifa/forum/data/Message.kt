package com.arkadya.chifa.forum.data

data class Message(
    val id: Long,
    val chatRoomId: Long,
    val senderId: String,
    val senderName: String,
    val content: String,
    val replyToMessageId: Long?,
    val timestamp: Long
)