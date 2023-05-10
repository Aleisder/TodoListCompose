package com.aleisder.todolistcompose.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aleisder.todolistcompose.repository.TodoListDao
import com.aleisder.todolistcompose.model.Todo
import com.aleisder.todolistcompose.model.TodoEvent
import com.aleisder.todolistcompose.model.TodoState
import com.aleisder.todolistcompose.util.DateConverter
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

    private val _futureTodos = dao
        .selectFutureTodos()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )

    val state = combine(
        _state,
        _todayTodos,
        _tomorrowTodos,
        _futureTodos
    ) { state, todayTodos, tomorrowTodos, futureTodos ->
        state.copy(
            todayTodos = todayTodos,
            tomorrowTodos = tomorrowTodos,
            futureTodos = futureTodos
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
                                dueDate = dueDate
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
            TodoEvent.SetTodayDate -> {
                _state.update {
                    it.copy(dueDate = DateConverter.getTodayDate())
                }
            }
            TodoEvent.SetTomorrowDate -> {
                _state.update {
                    it.copy(dueDate = DateConverter.getTomorrowDate())
                }
            }
            TodoEvent.SetUnknownDate -> {
                _state.update {
                    it.copy(dueDate = "")
                }
            }
            TodoEvent.CollapseFuture -> {
                _state.update {
                    it.copy(isFutureExpanded = false)
                }
            }
            TodoEvent.CollapseToday -> {
                _state.update {
                    it.copy(isTodayExpanded = false)
                }
            }
            TodoEvent.CollapseTomorrow -> {
                _state.update {
                    it.copy(isTomorrowExpanded = false)
                }
            }
            TodoEvent.ExpandFuture -> {
                _state.update {
                    it.copy(isFutureExpanded = true)
                }
            }
            TodoEvent.ExpandToday -> {
                _state.update {
                    it.copy(isTodayExpanded = true)
                }
            }
            TodoEvent.ExpandTomorrow -> {
                _state.update {
                    it.copy(isTomorrowExpanded = true)
                }
            }
        }
    }

}