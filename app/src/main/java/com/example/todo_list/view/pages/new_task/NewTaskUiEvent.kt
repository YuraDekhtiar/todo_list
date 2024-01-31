package com.example.todo_list.view.pages.new_task

import com.example.todo_list.base.UiEvent

sealed class NewTaskUiEvent: UiEvent {
    data object OnSaveClick : NewTaskUiEvent()
    data object OnBackClick : NewTaskUiEvent()
    data class OnLoadingUiData(val id: Int) : NewTaskUiEvent()
}