package com.aleisder.todolistcompose.ui.screens.currenttodosscreen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aleisder.todolistcompose.model.Todo
import com.aleisder.todolistcompose.model.TodoEvent
import com.aleisder.todolistcompose.model.TodoState
import com.aleisder.todolistcompose.ui.screens.TodoListViewModel
import com.aleisder.todolistcompose.ui.theme.CarlitoFamily
import com.aleisder.todolistcompose.ui.theme.DetailedTodoCardShape
import com.aleisder.todolistcompose.ui.theme.Shapes

@Composable
fun DetailedTodoScreen(
    state: TodoState,
    onEvent: (TodoEvent) -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = DetailedTodoCardShape
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(10.dp)
        ) {
            Divider(
                modifier = Modifier
                    .width(30.dp)
                    .clip(RoundedCornerShape(4.dp)),
                color = Color.Gray,
                thickness = 3.dp
            )



            OutlinedTextField(
                value = "",
                onValueChange = {  },
                textStyle = TextStyle(
                    fontSize = 25.sp,
                    fontFamily = CarlitoFamily,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .border(
                        width = 3.dp,
                        color = MaterialTheme.colors.background
                    )
                    .fillMaxWidth()


            )

            OutlinedTextField(
                value = "",
                onValueChange = {  },
                shape = Shapes.small,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)

            )


        }

    }
}