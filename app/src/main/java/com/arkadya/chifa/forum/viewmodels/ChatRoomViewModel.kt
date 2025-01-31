package com.arkadya.chifa.forum.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arkadya.chifa.forum.database.MessageDao
import com.arkadya.chifa.forum.entities.MessageEntity
import com.arkadya.chifa.forum.data.Message
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ChatRoomViewModel(
    private val roomName: String,
    private val messageDao: MessageDao
) : ViewModel() {
    val messages = messageDao.getMessagesByRoom(getRoomId(roomName))
        .map { entities -> entities.map { it.toMessage() } }
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            emptyList()
        )
    fun deleteMessage(message: Message) {
        viewModelScope.launch {
            messageDao.deleteMessage(MessageEntity(
                id = message.id,
                chatRoomId = message.chatRoomId,
                senderId = message.senderId,
                senderName = message.senderName,
                message = message.content,
                replyToMessageId = message.replyToMessageId,
                timestamp = message.timestamp
            ))
        }
    }

    fun updateMessage(messageId: Long, newContent: String) {
        viewModelScope.launch {
            val message = messages.value.find { it.id == messageId } ?: return@launch
            messageDao.updateMessage(MessageEntity(
                id = message.id,
                chatRoomId = message.chatRoomId,
                senderId = message.senderId,
                senderName = message.senderName,
                message = newContent,
                replyToMessageId = message.replyToMessageId,
                timestamp = message.timestamp
            ))
        }
    }

    fun sendMessage(content: String, replyToMessageId: Long? = null) {
        viewModelScope.launch {
            val message = MessageEntity(
                chatRoomId = getRoomId(roomName),
                senderId = "currentUserId", // Replace with actual user ID
                senderName = "Current User", // Replace with actual user name
                message = content,
                replyToMessageId = replyToMessageId
            )
            messageDao.insertMessage(message)
        }
    }

    private fun getRoomId(roomName: String): Long {
        return when(roomName) {
            "General Discussion" -> 1L
            "Technical Support" -> 2L
            "Feature Requests" -> 3L
            "Bug Reports" -> 4L
            "Community Events" -> 5L
            "Off-Topic" -> 6L
            else -> throw IllegalArgumentException("Unknown room name: $roomName")
        }
    }
}


// Extension function to convert MessageEntity to Message
private fun MessageEntity.toMessage(): Message {
    return Message(
        id = id,
        chatRoomId = chatRoomId,
        senderId = senderId,
        senderName = senderName,
        content = message,
        replyToMessageId = replyToMessageId,
        timestamp = timestamp
    )
}
