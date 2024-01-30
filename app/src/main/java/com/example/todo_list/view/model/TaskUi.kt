package com.example.todo_list.view.model

data class TaskUi (
    val taskId: Int,
    var description: String,
    var time: String,
    var date: String,
    val isDone: Boolean
)
