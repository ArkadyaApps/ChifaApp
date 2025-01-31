package com.arkadya.chifa.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arkadya.chifa.R
import com.arkadya.chifa.ui.screens.SearchResult
import com.arkadya.chifa.utils.LanguageUtils
import com.arkadya.chifa.navigation.NavRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

data class UiState(
    val currentLanguage: String = "en",
    val searchResults: List<SearchResult> = emptyList(),
    val isSearching: Boolean = false
)

@HiltViewModel
class YourViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _navigationEvent = MutableSharedFlow<String>()
    val navigationEvent = _navigationEvent.asSharedFlow()

    init {
        _uiState.update {
            it.copy(
                currentLanguage = LanguageUtils.getCurrentLanguage(context)
            )
        }
    }

    fun updateLanguage(context: Context) {
        val currentLang = _uiState.value.currentLanguage
        val nextLang = LanguageUtils.getNextLanguage(currentLang)

        Log.d("YourViewModel", "Switching language from $currentLang to $nextLang")

        viewModelScope.launch {
            _uiState.update { it.copy(currentLanguage = nextLang) }
            LanguageUtils.setLocale(context, nextLang)
        }
    }

    fun onButton2Click() {
        viewModelScope.launch {
            _navigationEvent.emit(NavRoute.PROXIMITY)
        }
    }

    fun onButton3Click() {
        viewModelScope.launch {
            _navigationEvent.emit(NavRoute.HEALING)
        }
    }

    fun onButton4Click() {
        viewModelScope.launch {
            _navigationEvent.emit(NavRoute.FORUM)
        }
    }

    fun onSearchSubmit(query: String) {
        Log.d("YourViewModel", "Search submitted: $query")
        viewModelScope.launch {
            _navigationEvent.emit(NavRoute.createSearchResultsRoute(query))
        }
        performSearch(query)
    }

    fun onResultClick(result: SearchResult) {
        viewModelScope.launch {
            when (result.type) {
                "healing" -> _navigationEvent.emit(NavRoute.HEALING)
                "proximity" -> _navigationEvent.emit(NavRoute.PROXIMITY)
                "forum" -> _navigationEvent.emit(NavRoute.FORUM)
                // Add navigation to chat room if applicable
                "chat_room" -> _navigationEvent.emit(NavRoute.createChatRoomRoute(result.query)) // Assuming result.query holds the room name
            }
        }
    }

    private fun performSearch(query: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isSearching = true) }
            try {
                val results = listOf(
                    createHealingResult(query),
                    createForumResult(query),
                    createProximityResult(query)
                )
                _uiState.update { it.copy(searchResults = results) }
            } finally {
                _uiState.update { it.copy(isSearching = false) }
            }
        }
    }

    private fun createHealingResult(query: String) = SearchResult(
        id = "1",
        titleResId = R.string.result_healing_title,
        descriptionResId = R.string.result_healing_description,
        type = "healing",
        query = query
    )

    private fun createForumResult(query: String) = SearchResult(
        id = "2",
        titleResId = R.string.result_forum_title,
        descriptionResId = R.string.result_forum_description,
        type = "forum",
        query = query
    )

    private fun createProximityResult(query: String) = SearchResult(
        id = "3",
        titleResId = R.string.result_proximity_title,
        descriptionResId = R.string.result_proximity_description,
        type = "proximity",
        query = query
    )
}