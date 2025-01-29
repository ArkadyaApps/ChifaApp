package com.arkadya.chifa.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.arkadya.chifa.viewmodels.YourViewModel

@Composable
fun AppNavigationHost(
    navController: NavHostController,
    viewModel: YourViewModel
) {
    val uiState = viewModel.uiState.collectAsState().value

    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(
                onButton1Click = { navController.navigate("screen1") },
                onButton2Click = { navController.navigate("screen2") },
                onButton3Click = { navController.navigate("screen3") },
                onButton4Click = { navController.popBackStack() },
                onSearchSubmit = { query -> navController.navigate("search/$query") },
                currentLanguage = uiState.currentLanguage,
                onLanguageChange = { newLanguage -> viewModel.updateLanguage(newLanguage) }
            )
        }
    }
}