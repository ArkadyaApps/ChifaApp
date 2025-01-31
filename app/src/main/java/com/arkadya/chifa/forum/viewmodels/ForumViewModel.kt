package com.arkadya.chifa.forum.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.arkadya.chifa.forum.database.MessageDao
import com.arkadya.chifa.forum.entities.MessageEntity
import com.arkadya.chifa.forum.data.Message
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.get
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import javax.inject.Inject

@HiltViewModel
class ForumViewModel @Inject constructor(
    private val messageDao: MessageDao
) : ViewModel() {
    private val _roomName = MutableStateFlow("")
    val roomName: StateFlow<String> = _roomName

    private val _messageText = MutableStateFlow("")
    val messageText: StateFlow<String> = _messageText

    val messages = messageDao.getMessagesByRoom(getRoomId(_roomName.value))
        .map { entities ->
            entities.map { entity ->
                Message(
                    id = entity.id,
                    chatRoomId = entity.chatRoomId,
                    senderId = entity.senderId,
                    senderName = entity.senderName,
                    content = entity.message,
                    replyToMessageId = entity.replyToMessageId,
                    timestamp = entity.timestamp
                )
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            emptyList()
        )

    fun updateMessageText(text: String) {
        _messageText.value = text
    }

    fun updateRoomName(text: String) {
        _roomName.value = text
    }

    fun sendMessage() {
        val messageText = _messageText.value
        if (messageText.isNotBlank()) {
            viewModelScope.launch {
                messageDao.insertMessage(
                    MessageEntity(
                        chatRoomId = getRoomId(_roomName.value),
                        senderId = "currentUserId", // Replace with actual user ID
                        senderName = "Current User", // Replace with actual user name
                        message = messageText,
                        timestamp = System.currentTimeMillis()
                    )
                )
                _messageText.value = ""
            }
        }
    }

    private fun getRoomId(roomName: String): Long {
        return when (roomName) {
            "General Discussion" -> 1L
            "Technical Support" -> 2L
            "Feature Requests" -> 3L
            "Bug Reports" -> 4L
            "Community Events" -> 5L
            "Off-Topic" -> 6L
            else -> throw IllegalArgumentException("Unknown room name: $roomName")
        }
    }

    companion object {
        // Define a custom key for your dependency
        val ROOM_NAME_KEY = object : CreationExtras.Key<String> {}

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val roomName = this[ROOM_NAME_KEY] ?: ""
                val messageDao: MessageDao = getMessageDao(this)
                ForumViewModel(messageDao).apply {
                    updateRoomName(roomName)
                }
            }
        }
        private fun getMessageDao(extras: CreationExtras): MessageDao {
            return extras.createSavedStateHandle().get<MessageDao>("messageDao")!!
        }
    }
}