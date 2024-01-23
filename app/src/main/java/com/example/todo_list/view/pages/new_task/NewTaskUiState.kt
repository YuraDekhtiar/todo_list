package com.example.todo_list.view.pages.new_task

import com.example.todo_list.base.UiState
import com.example.todo_list.view.model.NewTask
import com.example.todo_list.view.model.TaskUi

data class NewTaskUiState(
    val newTask: NewTask
) : UiState