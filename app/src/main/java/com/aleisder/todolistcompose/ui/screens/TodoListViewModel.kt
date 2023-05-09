package com.aleisder.todolistcompose.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aleisder.todolistcompose.repository.TodoListDao
import com.aleisder.todolistcompose.model.Todo
import com.aleisder.todolistcompose.model.TodoEvent
import com.aleisder.todolistcompose.model.TodoState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TodoListViewModel(
    private val dao: TodoListDao
) : ViewModel() {

    private val _state = MutableStateFlow(TodoState())

    private val _todayTodos = dao
        .selectTodayTodos()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )

    private val _tomorrowTodos = dao
        .selectTomorrowTodos()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )

    val state = combine(_state, _todayTodos, _tomorrowTodos) { state, todayTodos, tomorrowTodos ->
        state.copy(
            todayTodos = todayTodos,
            tomorrowTodos = tomorrowTodos
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = TodoState()
    )

    fun onEvent(event: TodoEvent) {
        when (event) {
            is TodoEvent.DeleteTodo -> {
                viewModelScope.launch {
                    dao.deleteTodo(event.todo)
                }
            }
            TodoEvent.HideDialog -> {
                _state.update { it.copy(isAddingTodo = false) }
            }
            TodoEvent.SaveTodo -> {
                _state.value.apply {
                    viewModelScope.launch {
                        dao.insertTodo(
                            Todo(
                                title = title,
                                note = description,
                                must_be_done_at = dueDate
                            )
                        )
                    }
                }
                _state.update {
                    it.copy(
                        title = "",
                        description = "",
                        dueDate = "",
                        isAddingTodo = false
                    )
                }
            }
            TodoEvent.ShowDialog -> {
                _state.update {
                    it.copy(isAddingTodo = true)
                }
            }
            is TodoEvent.SetDescription -> {
                _state.update { it.copy(description = event.description) }
            }
            is TodoEvent.SetTitle -> {
                _state.update { it.copy(title = event.title) }
            }
            is TodoEvent.SetDueDate -> {
                _state.update { it.copy(dueDate = event.dueDate) }
            }
            is TodoEvent.ChangeIsChecked -> {
                viewModelScope.launch {
                    dao.updateIsDone(
                        id = event.todo.id,
                        isDone = !event.todo.isDone
                    )
                }
            }
            TodoEvent.HideTodoDetails -> {
                _state.update {
                    it.copy(isShowingDetails = false)
                }
            }
            TodoEvent.ShowTodoDetails -> {
                _state.update {
                    it.copy(isShowingDetails = true)
                }
            }
        }
    }

    fun updateIsDone(id: Int, isDone: Boolean) {
        viewModelScope.launch {
            dao.updateIsDone(id, isDone)
        }
    }

}