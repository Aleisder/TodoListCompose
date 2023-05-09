package com.aleisder.todolistcompose.repository

import androidx.room.*
import com.aleisder.todolistcompose.model.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoListDao {

    @Upsert
    suspend fun insertTodo(todo: Todo)

    @Query("UPDATE todo SET is_done = :isDone WHERE id = :id")
    suspend fun updateIsDone(id: Int, isDone: Boolean)

    @Delete
    suspend fun deleteTodo(todo: Todo)

    @Query("SELECT * FROM todo WHERE must_be_done_at = DATE() ORDER BY is_done ASC")
    fun selectTodayTodos(): Flow<List<Todo>>

    @Query("SELECT * FROM todo WHERE must_be_done_at = DATE('NOW', '+1 DAY') ORDER BY is_done ASC")
    fun selectTomorrowTodos(): Flow<List<Todo>>

}