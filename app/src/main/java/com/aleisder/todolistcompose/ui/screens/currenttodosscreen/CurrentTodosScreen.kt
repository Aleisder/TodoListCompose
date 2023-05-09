package com.aleisder.todolistcompose.ui.screens.currenttodosscreen

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aleisder.todolistcompose.R
import com.aleisder.todolistcompose.model.Todo
import com.aleisder.todolistcompose.model.TodoEvent
import com.aleisder.todolistcompose.model.TodoState
import com.aleisder.todolistcompose.ui.theme.TitleTextStyle
import com.aleisder.todolistcompose.util.DateConverter

@Composable
fun CurrentTodosScreen(
    state: TodoState,
    onEvent: (TodoEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 20.dp, start = 15.dp, end = 15.dp)
    ) {
        Column {
            TodoList(
                title = R.string.today,
                todos = state.todayTodos,
                setDueDate = { onEvent(TodoEvent.SetDueDate(DateConverter.getTodayDate())) },
                state = state,
                onEvent = onEvent
            )
            TodoList(
                title = R.string.tomorrow,
                todos = state.tomorrowTodos,
                setDueDate = { onEvent(TodoEvent.SetDueDate(DateConverter.getTomorrowDate())) },
                state = state,
                onEvent = onEvent
            )
            TodoList(
                title = R.string.future,
                todos = emptyList(),
                setDueDate = { onEvent(TodoEvent.SetDueDate(DateConverter.getTodayDate())) },
                state = state,
                onEvent = onEvent
            )
        }

    }
}

@Composable
fun TodoList(
    @StringRes title: Int,
    todos: List<Todo>,
    setDueDate: () -> Unit,
    state: TodoState,
    onEvent: (TodoEvent) -> Unit
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }

    if (state.isAddingTodo) {
        AddTodoDialog(
            state = state,
            onEvent = onEvent
        )
    }

    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = if (isExpanded) Icons.Filled.KeyboardArrowUp
                else Icons.Filled.KeyboardArrowDown,
                contentDescription = null
            )
            Text(
                text = stringResource(title),
                style = TitleTextStyle,
                modifier = Modifier.clickable {
                    isExpanded = !isExpanded
                }
            )

            Spacer(Modifier.weight(1f))

            Icon(
                Icons.Filled.Add,
                contentDescription = null,
                modifier = Modifier.clickable {
                    setDueDate()
                    onEvent(TodoEvent.ShowDialog)
                }
            )
        }

        AnimatedVisibility(
            visible = isExpanded,
            enter = slideInVertically(),
            exit = slideOutVertically()
        ) {
            LazyColumn {
                items(todos) { todoItem ->
                    TodoItem(
                        todo = todoItem,
                        state = state,
                        onEvent = onEvent
                    )
                }
            }
        }

    }

}

@Composable
fun TodoItem(
    todo: Todo,
    state: TodoState,
    onEvent: (TodoEvent) -> Unit
) {

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(
                checked = todo.isDone,
                onCheckedChange = {
                    onEvent(TodoEvent.ChangeIsChecked(todo))
                }
            )
            Text(
                text = todo.title,
                color = if (todo.isDone) Color.Gray else Color.Black,
                modifier = Modifier.clickable {
                    onEvent(TodoEvent.ShowTodoDetails)
                }
            )

            Spacer(modifier = Modifier.weight(1f))

            AnimatedVisibility(
                visible = todo.isDone
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.clickable {
                        onEvent(TodoEvent.DeleteTodo(todo))
                    }
                )
            }


        }

        AnimatedVisibility(
            visible = todo.isDone,
            enter = slideInHorizontally()
        ) {
            Divider(
                modifier = Modifier.fillMaxWidth(),
                color = Color.Gray,
                thickness = 2.dp
            )
        }


    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CurrentTodosScreenPreview() {
    //CurrentTodosScreen(viewModel = TodoListViewModel(dao = ))
}