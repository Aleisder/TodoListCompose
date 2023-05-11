package com.aleisder.todolistcompose.util

import android.util.Log
import java.time.LocalDate

object DateConverter {

    fun getTodayDate() = LocalDate.now().toSqlDateFormat()

    fun getTomorrowDate(): String {
        val tomorrow = LocalDate.now().plusDays(1).toSqlDateFormat()
        Log.e("DateConverter", tomorrow)
        return tomorrow
    }

}