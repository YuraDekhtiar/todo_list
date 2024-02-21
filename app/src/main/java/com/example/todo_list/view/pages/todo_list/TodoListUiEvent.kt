package com.example.todo_list.view.pages.todo_list

import com.example.todo_list.base.UiEvent
import com.example.todo_list.view.model.TaskUi

sealed class TodoListUiEvent : UiEvent {
    data class OnCheckClick(val task: TaskUi) : TodoListUiEvent()
    data class OnEditClick(val id: Int) : TodoListUiEvent()
    data class OnDeleteClick(val id: Int) : TodoListUiEvent()
    data class OnTaskClick(val id: Int) : TodoListUiEvent()
    data object OnAddClick : TodoListUiEvent()
    data class OnSearchUpdate(val text: String) : TodoListUiEvent()
    data object OnSearchClear : TodoListUiEvent()
    data object OnLoadingUiData : TodoListUiEvent()
}

