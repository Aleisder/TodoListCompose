package com.aleisder.todolistcompose.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val title: String,

    @ColumnInfo(name = "is_done")
    val isDone: Boolean = false,

    val note: String,

    val must_be_done_at: String
)
