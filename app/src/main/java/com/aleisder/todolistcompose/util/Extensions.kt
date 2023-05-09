package com.aleisder.todolistcompose.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun LocalDate.toSqlDateFormat(): String {
    return DateTimeFormatter
        .ofPattern("yyyy-MM-dd")
        .format(this)
}