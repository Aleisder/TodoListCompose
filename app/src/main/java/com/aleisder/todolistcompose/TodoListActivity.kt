package com.aleisder.todolistcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aleisder.todolistcompose.ui.screens.TodoListViewModel
import com.aleisder.todolistcompose.repository.TodoListDatabase
import com.aleisder.todolistcompose.ui.components.TodoListBottomNavigationBar
import com.aleisder.todolistcompose.ui.screens.calendarscreen.CalendarScreen
import com.aleisder.todolistcompose.ui.screens.currenttodosscreen.CurrentTodosScreen
import com.aleisder.todolistcompose.ui.theme.TodoListComposeTheme
import com.aleisder.todolistcompose.util.Routes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoListComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    val dao = TodoListDatabase.getInstance(this).getTodoListDao()
                    val viewModel = TodoListViewModel(dao)

                    App(
                        navController = navController,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}

@Composable
fun App(
    navController: NavHostController,
    viewModel: TodoListViewModel
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        bottomBar = {
            BottomNavigation(
                backgroundColor = Color.White
            ) {
                TodoListBottomNavigationBar(
                    navController = navController
                )
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Routes.CurrentTodos.route
        ) {
            composable(Routes.CurrentTodos.route) {
                CurrentTodosScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    modifier = Modifier.padding(paddingValues)
                )
            }

            composable(Routes.Calendar.route) {
                CalendarScreen()
            }

        }
    }
}
