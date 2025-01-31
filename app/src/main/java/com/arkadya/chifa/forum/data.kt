package com.arkadya.chifa.forum

data class ChatMessage(
    val id: String = "",
    val senderId: String = "",
    val message: String = "",
    val timestamp: Long = System.currentTimeMillis(),
    val senderName: String = ""
)
