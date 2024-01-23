package com.example.todo_list.view.mapper

import com.example.todo_list.domain.model.TaskDomain
import com.example.todo_list.view.model.TaskUi
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun TaskDomain.toTaskUi(): TaskUi {
    val simpleDateFormat = SimpleDateFormat("HH:mm", Locale.ENGLISH)

    return TaskUi(
        taskId = this.taskId,
        description = this.description,
        isDone = this.isDone,
        time = simpleDateFormat.format(Date(this.time)),
    )
}
