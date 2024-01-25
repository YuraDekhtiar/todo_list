package com.example.todo_list.view.pages.todo_list

import com.example.todo_list.base.UiState
import com.example.todo_list.view.model.TaskUi

data class TodoListUiState(
    val tasks: List<TaskUi> = emptyList(),
    val search: String = "",
) : UiState