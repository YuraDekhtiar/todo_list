package com.example.todo_list.pages.todo_list

import com.example.todo_list.base.UiState
import com.example.todo_list.database.entities.Task

data class TodoListUiState(
    val tasks: List<Task> = emptyList(),
    val search: String = ""
) : UiState