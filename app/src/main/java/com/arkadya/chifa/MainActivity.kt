package com.arkadya.chifa

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.arkadya.chifa.forum.ui.screens.ForumScreen
import com.arkadya.chifa.forum.ui.screens.ChatRoomsScreen
import com.arkadya.chifa.forum.ui.screens.ChatRoomScreen
import com.arkadya.chifa.navigation.NavRoute
import com.arkadya.chifa.ui.screens.HealingScreen
import com.arkadya.chifa.ui.screens.MainScreen
import com.arkadya.chifa.ui.screens.ProximityScreen
import com.arkadya.chifa.ui.screens.ResultScreen
import com.arkadya.chifa.ui.theme.ChifaTheme
import com.arkadya.chifa.utils.LanguageUtils
import com.arkadya.chifa.viewmodels.YourViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: YourViewModel by viewModels()

    override fun attachBaseContext(newBase: Context) {
        val currentLanguage = LanguageUtils.getCurrentLanguage(newBase)
        super.attachBaseContext(LanguageUtils.createContextWithNewLocale(newBase, currentLanguage))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val navController = rememberNavController()

            LaunchedEffect(Unit) {
                viewModel.navigationEvent.collect { route ->
                    navController.navigate(route)
                }
            }

            ChifaTheme {
                CompositionLocalProvider(
                    LocalLayoutDirection provides if (uiState.currentLanguage == "ar")
                        LayoutDirection.Rtl else LayoutDirection.Ltr
                ) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = NavRoute.MAIN
                        ) {
                            composable(NavRoute.MAIN) {
                                MainScreen(
                                    onButton1Click = { /* your navigation */ },
                                    onButton2Click = { viewModel.onButton2Click() },
                                    onButton3Click = { viewModel.onButton3Click() },
                                    onButton4Click = { viewModel.onButton4Click() },
                                    onSearchSubmit = viewModel::onSearchSubmit,
                                    currentLanguage = uiState.currentLanguage,
                                    onLanguageChange = viewModel::updateLanguage
                                )
                            }
                            composable(NavRoute.PROXIMITY) {
                                ProximityScreen(navController = navController)
                            }
                            composable(NavRoute.HEALING) {
                                HealingScreen(navController = navController)
                            }
                            composable(NavRoute.FORUM) { backStackEntry ->
                                val roomName = backStackEntry.arguments?.getString("roomName") ?: "Default Room Name"
                                ForumScreen(navController = navController, roomName = roomName)
                            }
                            composable(NavRoute.CHAT_ROOMS) {
                                ChatRoomsScreen(navController = navController)
                            }
                            composable(
                                route = NavRoute.SEARCH_RESULTS,
                                arguments = listOf(
                                    navArgument("query") { type = NavType.StringType }
                                )
                            ) { backStackEntry ->
                                val query = backStackEntry.arguments?.getString("query") ?: ""
                                ResultScreen(
                                    query = query,
                                    results = uiState.searchResults,
                                    isLoading = uiState.isSearching,
                                    navController = navController,
                                    onResultClick = viewModel::onResultClick,
                                    currentLanguage = uiState.currentLanguage
                                )
                            }
                            composable(NavRoute.CHAT_ROOM) { backStackEntry ->
                                val roomName = backStackEntry.arguments?.getString("roomName") ?: "Default Room"
                                ChatRoomScreen(roomName = roomName, navController = navController)
                            }
                        }
                    }
                }
            }
        }
    }
}