package com.arkadya.chifa.ui.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.arkadya.chifa.R

data class LanguageOption(
    val code: String,
    val label: String,
    val displayName: String
)

@Composable
fun LanguageSelector(
    currentLanguage: String,
    onLanguageChange: (Context) -> Unit
) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }

    val languages = listOf(
        LanguageOption("en", "EN", stringResource(R.string.language_english)),
        LanguageOption("fr", "FR", stringResource(R.string.language_french)),
        LanguageOption("ar", "عربي", stringResource(R.string.language_arabic))
    )

    IconButton(
        onClick = { expanded = true },
        modifier = Modifier.padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.language),
            contentDescription = stringResource(R.string.language_selection),
            modifier = Modifier.size(24.dp)  // Adjust size as needed
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            languages.forEach { language ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = "${language.displayName} (${language.label})"
                        )
                    },
                    onClick = {
                        if (currentLanguage != language.code) {
                            onLanguageChange(context)
                        }
                        expanded = false
                    }
                )
            }
        }
    }
}