package com.example.todo_list.view.pages.new_task

import com.example.todo_list.base.UiState
import com.example.todo_list.view.model.NewTask

data class NewTaskUiState(
    // TODO get this data from repository
    val newTask: NewTask = NewTask.emptyTask()
) : UiState