package com.example.todo_list.view.pages.new_task

import com.example.todo_list.base.UiEvent
import com.example.todo_list.view.model.TaskUi
import com.example.todo_list.view.pages.todo_list.TodoListUiEvent

sealed class NewTaskUiEvent: UiEvent {
    data class OnSaveClick(val task: TaskUi) : NewTaskUiEvent()
    data object OnBackClick : NewTaskUiEvent()
    data object OnLoadingUiData : NewTaskUiEvent()
}