package com.arkadya.chifa.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.arkadya.chifa.forum.viewmodels.ChatRoomViewModel

@Composable
fun ChatRoomsScreen(
    navController: NavController,
    viewModel: ChatRoomViewModel = viewModel()
) {
    val chatRooms = listOf(
        "General Discussion",
        "Technical Support",
        "Feature Requests",
        "Bug Reports",
        "Community Events",
        "Off-Topic"
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(chatRooms) { roomName ->
            ChatRoomItem(
                roomName = roomName,
                onRoomClick = {
                    navController.navigate("chatroom/$roomName")
                }
            )
        }
    }
}

@Composable
private fun ChatRoomItem(
    roomName: String,
    onRoomClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onRoomClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = roomName,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}