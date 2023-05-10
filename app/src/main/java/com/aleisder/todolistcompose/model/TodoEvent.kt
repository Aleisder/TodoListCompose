package com.aleisder.todolistcompose.model

sealed interface TodoEvent {
    data class SetTitle(val title: String) : TodoEvent
    data class SetDescription(val description: String) : TodoEvent
    object SetTodayDate : TodoEvent
    object SetTomorrowDate : TodoEvent
    object SetUnknownDate : TodoEvent
    object SaveTodo : TodoEvent
    data class ChangeIsChecked(val todo: Todo) : TodoEvent
    object ShowDialog : TodoEvent
    object HideDialog : TodoEvent
    data class DeleteTodo(val todo: Todo) : TodoEvent
    object ShowTodoDetails : TodoEvent
    object HideTodoDetails: TodoEvent
    object ExpandToday : TodoEvent
    object CollapseToday : TodoEvent
    object ExpandTomorrow : TodoEvent
    object CollapseTomorrow : TodoEvent
    object ExpandFuture : TodoEvent
    object CollapseFuture : TodoEvent
}
