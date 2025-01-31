package com.arkadya.chifa.navigation

object NavRoute {
    // Main application route
    const val MAIN = "main"

    // Proximity-based features
    const val PROXIMITY = "proximity"

    // Healing-related features
    const val HEALING = "healing"

    // Forum section
    const val FORUM = "forum"

    // Search results with a query parameter
    const val SEARCH_RESULTS = "search_results?query={query}"

    // Chat rooms overview
    const val CHAT_ROOMS = "chat_rooms"

    // Individual chat room with a dynamic room name
    const val CHAT_ROOM = "chatroom/{roomName}"

    // Function to create a route for the chat room with a specific room name
    fun createChatRoomRoute(roomName: String): String {
        return "chatroom/$roomName"
    }

    // Function to create a route for search results with a specific query
    fun createSearchResultsRoute(query: String): String {
        return "search_results?query=$query"
    }
}