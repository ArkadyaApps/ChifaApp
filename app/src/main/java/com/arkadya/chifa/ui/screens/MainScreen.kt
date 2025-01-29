package com.arkadya.chifa.ui.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.arkadya.chifa.R





@Composable
fun MainScreen(
    onButton1Click: () -> Unit,
    onButton2Click: () -> Unit,
    onButton3Click: () -> Unit,
    onButton4Click: () -> Unit,
    onSearchSubmit: (String) -> Unit,
    currentLanguage: String,
    onLanguageChange: (Context) -> Unit
) {
    CompositionLocalProvider(
        LocalLayoutDirection provides if (currentLanguage == "ar")
            LayoutDirection.Rtl else LayoutDirection.Ltr
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Background
            Image(
                painter = painterResource(id = R.drawable.background_image),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit
            )

            // Content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Language Selector
                LanguageSelector(
                    currentLanguage = currentLanguage,
                    onLanguageChange = onLanguageChange
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Search Box
                SearchBox(onSearchSubmit = onSearchSubmit)

                Spacer(modifier = Modifier.weight(1f))

                // Main Buttons
                MainButtons(
                    onButton1Click = onButton1Click,
                    onButton2Click = onButton2Click,
                    onButton3Click = onButton3Click,
                    onButton4Click = onButton4Click
                )
            }
        }
    }
}


@Composable
private fun SearchBox(onSearchSubmit: (String) -> Unit) {
    var searchText by remember { mutableStateOf("") }

    TextField(
        value = searchText,
        onValueChange = { searchText = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(25.dp),
        colors = TextFieldDefaults.colors(
            // Change background color to light gray
            focusedContainerColor = Color(0xFFD1D3FD),
            unfocusedContainerColor = Color(0xFF5CB787),
            disabledContainerColor = Color(0xFF5D71D5),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        placeholder = { Text(stringResource(R.string.search_placeholder)) },
        trailingIcon = {
            IconButton(onClick = { onSearchSubmit(searchText) }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = stringResource(R.string.search_icon_description),
                    tint = Color(0xFF5D71D5),
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    )
}

@Composable
private fun MainButtons(
    onButton1Click: () -> Unit,
    onButton2Click: () -> Unit,
    onButton3Click: () -> Unit,
    onButton4Click: () -> Unit
) {
    Surface(
        color = Color(0xFF6E96FF), // Light blue background
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            MainButton(
                text = stringResource(R.string.b1),
                onClick = onButton1Click,
                modifier = Modifier.weight(1f),
                iconId = R.drawable.ic_home
            )
            MainButton(
                text = stringResource(R.string.b2),
                onClick = onButton2Click,
                modifier = Modifier.weight(1f),
                iconId = R.drawable.ic_nearby
            )
            MainButton(
                text = stringResource(R.string.b3),
                onClick = onButton3Click,
                modifier = Modifier.weight(1f),
                iconId = R.drawable.ic_online
            )
            MainButton(
                text = stringResource(R.string.b4),
                onClick = onButton4Click,
                modifier = Modifier.weight(1f),
                iconId = R.drawable.ic_search
            )
        }
    }
}

@Composable
private fun MainButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    iconId: Int
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .aspectRatio(1f)
            .clip(CircleShape),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF3458DA)
        ),
        contentPadding = PaddingValues(2.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = iconId),
                contentDescription = text,
                modifier = Modifier.fillMaxSize(0.95f),
                contentScale = ContentScale.Fit
            )
        }
    }
}