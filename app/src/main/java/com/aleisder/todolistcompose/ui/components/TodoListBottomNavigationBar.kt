package com.aleisder.todolistcompose.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.aleisder.todolistcompose.ui.theme.Green500
import com.aleisder.todolistcompose.ui.theme.NavigationBarCornerShape
import com.aleisder.todolistcompose.util.Routes

@Composable
fun TodoListBottomNavigationBar(
    navController: NavController
) {
    BottomNavigation(
        backgroundColor = Color.White,
        elevation = 1.dp,
        modifier = Modifier
            .clip(NavigationBarCornerShape)
            .border(
                width = 1.dp,
                color = Green500,
                shape = NavigationBarCornerShape
            )
    ) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        val routes = listOf(Routes.CurrentTodos, Routes.Calendar)

        routes.forEach {
            BottomNavigationItem(
                selected = currentRoute == it.route,
                onClick = {
                    navController.navigate(it.route) {
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(it.icon, contentDescription = null)
                },
                label = {
                    Text(text = it.title)
                },
                selectedContentColor = Green500,
                unselectedContentColor = Color.LightGray
            )
        }

    }
}
