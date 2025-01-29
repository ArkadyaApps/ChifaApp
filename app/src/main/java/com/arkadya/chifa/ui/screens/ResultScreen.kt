package com.arkadya.chifa.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.arkadya.chifa.R

data class SearchResult(
    val id: String,
    val titleResId: Int,
    val descriptionResId: Int,
    val type: String, // "healing", "forum", or "proximity"
    val query: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(
    query: String,
    results: List<SearchResult>,
    isLoading: Boolean,
    navController: NavController,
    onResultClick: (SearchResult) -> Unit,
    currentLanguage: String
) {
    CompositionLocalProvider(
        LocalLayoutDirection provides if (currentLanguage == "ar")
            LayoutDirection.Rtl else LayoutDirection.Ltr
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(stringResource(R.string.search_results_for, query))
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = stringResource(R.string.back)
                            )
                        }
                    }
                )
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                when {
                    isLoading -> {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                    results.isEmpty() -> {
                        Text(
                            text = stringResource(R.string.no_results_found),
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(16.dp)
                        )
                    }
                    else -> {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(16.dp)
                        ) {
                            items(results) { result ->
                                ResultItem(
                                    result = result,
                                    onClick = { onResultClick(result) }
                                )
                                HorizontalDivider(
                                    modifier = Modifier.padding(vertical = 8.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ResultItem(
    result: SearchResult,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "${stringResource(result.titleResId)} ${result.query}",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(result.descriptionResId),
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = when (result.type) {
                    "healing" -> stringResource(R.string.type_healing)
                    "forum" -> stringResource(R.string.type_forum)
                    "proximity" -> stringResource(R.string.type_proximity)
                    else -> result.type
                },
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}