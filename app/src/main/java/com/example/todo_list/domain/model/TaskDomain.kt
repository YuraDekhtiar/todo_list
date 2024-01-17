package com.example.todo_list.domain.model

data class TaskDomain(
    val taskId: Int,
    val description: String,
    val time: Long,
    val isDone: Boolean
)

