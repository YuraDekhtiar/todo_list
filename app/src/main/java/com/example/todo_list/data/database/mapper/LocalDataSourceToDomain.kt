package com.example.todo_list.data.database.mapper

import com.example.todo_list.data.database.entities.Task
import com.example.todo_list.domain.model.TaskDomain

fun Task.toDomain(): TaskDomain {
    return TaskDomain(
        taskId = this.taskId ?: -1,
        description = this.description.orEmpty(),
        time = this.time ?: -1,
        isDone = this.isDone ?: false
    )
}