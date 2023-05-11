package com.aleisder.todolistcompose.ui.screens.calendarscreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.aleisder.todolistcompose.util.DateConverter

@Composable
fun CalendarScreen() {
    Text(
        text = "CalendarScreen",
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxSize()
    )
}