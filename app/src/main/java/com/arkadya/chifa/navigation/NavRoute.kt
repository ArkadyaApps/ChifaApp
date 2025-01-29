package com.arkadya.chifa.navigation

sealed class NavRoute(val route: String) {
    data object Main : NavRoute("main")
    data object Proximity : NavRoute("proximity")
    data object Healing : NavRoute("healing")
    data object Forum : NavRoute("forum")
    data object SearchResults : NavRoute("search_results/{query}") {
        fun createRoute(query: String) = "search_results/$query"
    }
}