package com.example.todo_list.domain.datasource

import com.example.todo_list.domain.model.TaskDomain

interface TaskDataSource {
    fun getEmptyTask() : TaskDomain
}