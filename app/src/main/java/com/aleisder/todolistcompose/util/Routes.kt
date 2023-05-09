package com.aleisder.todolistcompose.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Routes(
    val route: String,
    val icon: ImageVector,
    val title: String
) {
    object CurrentTodos : Routes("current_todos", Icons.Outlined.Home, "Текущие")
    object Calendar : Routes("calendar", Icons.Outlined.Menu, "Календарь")
}
