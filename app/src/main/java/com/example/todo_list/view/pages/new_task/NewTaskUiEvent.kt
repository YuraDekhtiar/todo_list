package com.example.todo_list.view.pages.new_task

import com.example.todo_list.base.UiEvent
import com.example.todo_list.view.model.NewTask

sealed class NewTaskUiEvent: UiEvent {
    data class OnSaveClick(val newTask: NewTask) : NewTaskUiEvent()
    data object OnBackClick : NewTaskUiEvent()
    data object OnLoadingUiData : NewTaskUiEvent()
}