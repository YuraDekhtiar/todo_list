package com.example.todo_list.view.pages.new_task

import com.example.todo_list.base.UiState
import com.example.todo_list.data.datasource.TaskDataSourceImpl
import com.example.todo_list.domain.mapper.toTaskUi
import com.example.todo_list.view.model.TaskUi

data class NewTaskUiState(
    val task: TaskUi = TaskDataSourceImpl().getEmptyTask().toTaskUi()
) : UiState