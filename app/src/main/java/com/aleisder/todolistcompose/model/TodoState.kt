package com.aleisder.todolistcompose.model

data class TodoState(
    val todayTodos: List<Todo> = emptyList(),
    val tomorrowTodos: List<Todo> = emptyList(),
    val futureTodos: List<Todo> = emptyList(),
    val isTodayExpanded: Boolean = false,
    val isTomorrowExpanded: Boolean = false,
    val isFutureExpanded: Boolean = false,
    val title: String = "",
    val description: String = "",
    val dueDate: String = "",
    val isAddingTodo: Boolean = false,
    val isShowingDetails: Boolean = false
)
