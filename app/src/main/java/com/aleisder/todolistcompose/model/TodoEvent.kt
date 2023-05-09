package com.aleisder.todolistcompose.model

sealed interface TodoEvent {
    data class SetTitle(val title: String) : TodoEvent
    data class SetDescription(val description: String) : TodoEvent
    data class SetDueDate(val dueDate: String) : TodoEvent
    object SaveTodo : TodoEvent
    data class ChangeIsChecked(val todo: Todo) : TodoEvent
    object ShowDialog : TodoEvent
    object HideDialog : TodoEvent
    data class DeleteTodo(val todo: Todo) : TodoEvent
    object ShowTodoDetails : TodoEvent
    object HideTodoDetails: TodoEvent
}
