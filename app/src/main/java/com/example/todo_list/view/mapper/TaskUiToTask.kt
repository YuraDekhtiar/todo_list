package com.example.todo_list.view.mapper

import com.example.todo_list.base.utils.TimeDateFormat
import com.example.todo_list.data.database.entities.Task
import com.example.todo_list.view.model.TaskUi

fun TaskUi.toTask(): Task {

    return Task(
        taskId = if(this.taskId <= 0) null else this.taskId,
        description = this.description,
        time = TimeDateFormat.formatDateTimeToTimeStamp(
            time = this.time,
            date = this.date
        ),
        isDone = false,
    )
}