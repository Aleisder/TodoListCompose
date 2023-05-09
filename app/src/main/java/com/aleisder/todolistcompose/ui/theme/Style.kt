package com.aleisder.todolistcompose.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val TitleTextStyle = TextStyle(
    fontSize = 25.sp,
    fontFamily = CarlitoFamily,
    fontWeight = FontWeight.Bold
)

val NavigationBarCornerShape = RoundedCornerShape(
    topStart = 20.dp,
    topEnd = 20.dp
)

val DetailedTodoCardShape = RoundedCornerShape(
    topStart = 15.dp,
    topEnd = 15.dp
)