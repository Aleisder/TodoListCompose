package com.aleisder.todolistcompose.ui.screens.currenttodosscreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.aleisder.todolistcompose.model.TodoEvent
import com.aleisder.todolistcompose.model.TodoState
import com.aleisder.todolistcompose.ui.theme.Shapes
import com.aleisder.todolistcompose.util.DateConverter

@Composable
fun AddTodoDialog(
    state: TodoState,
    onEvent: (TodoEvent) -> Unit
) {
    Dialog(
        onDismissRequest = {
            onEvent(TodoEvent.HideDialog)
        }
    ) {
        Card(
            shape = Shapes.small
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ) {
                OutlinedTextField(
                    value = state.title,
                    onValueChange = {
                        onEvent(TodoEvent.SetTitle(it))
                    },
                    shape = Shapes.small,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = state.description,
                    onValueChange = {
                        onEvent(TodoEvent.SetDescription(it))
                    },
                    shape = Shapes.small,
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(80.dp, 140.dp)
                )

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedButton(
                        onClick = {
                            onEvent(TodoEvent.SaveTodo)
                        },
                        shape = Shapes.small
                    ) {
                        Text(text = "Add")
                    }

                }
            }


        }

    }
}