package com.arkadya.chifa.forum.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.arkadya.chifa.forum.viewmodels.ChatRoomViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatRoomsScreen(
    navController: NavController,
    viewModel: ChatRoomViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Chat Rooms") }
            )
        }
    ) { paddingValues ->
        val chatRooms = listOf(
            ChatRoom("General Discussion", "General topics and discussions"),
            ChatRoom("Technical Support", "Get help with technical issues"),
            ChatRoom("Feature Requests", "Suggest new features"),
            ChatRoom("Bug Reports", "Report bugs and issues"),
            ChatRoom("Community Events", "Upcoming events and meetups"),
            ChatRoom("Off-Topic", "Casual conversations")
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(chatRooms) { room ->
                ChatRoomCard(
                    chatRoom = room,
                    onRoomClick = {
                        navController.navigate("forum/${room.name}")
                    }
                )
            }
        }
    }
}

@Composable
private fun ChatRoomCard(
    chatRoom: ChatRoom,
    onRoomClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onRoomClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Chat,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = chatRoom.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = chatRoom.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

data class ChatRoom(
    val name: String,
    val description: String
)