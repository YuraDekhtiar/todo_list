package com.example.todo_list.data.datasource

import com.example.todo_list.domain.datasource.TaskDataSource
import com.example.todo_list.domain.model.TaskDomain

class TaskDataSourceImpl : TaskDataSource {
    override fun getEmptyTask(): TaskDomain {
        return TaskDomain(
            taskId = -1,
            description = "",
            isDone = false,
            // Get date time next day
            time = System.currentTimeMillis() + 86_400_000
        )
    }

}