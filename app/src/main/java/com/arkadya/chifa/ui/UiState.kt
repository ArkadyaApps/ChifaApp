
package com.arkadya.chifa.ui

import com.arkadya.chifa.data.local.entities.MyEntity

data class UiState(
    val currentLanguage: String = "en",
    val isLoading: Boolean = false,
    val error: String? = null,
    val data: List<MyEntity> = emptyList()
)