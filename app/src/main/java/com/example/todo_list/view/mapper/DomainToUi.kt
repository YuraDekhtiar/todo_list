package com.example.todo_list.view.mapper

import com.example.todo_list.data.database.entities.Task
import com.example.todo_list.domain.model.TaskDomain
import com.example.todo_list.view.TimeFormat
import com.example.todo_list.view.model.NewTask
import com.example.todo_list.view.model.TaskUi

fun TaskDomain.toTaskUi(): TaskUi {
    return TaskUi(
        taskId = this.taskId,
        description = this.description,
        isDone = this.isDone,
        time = TimeFormat.timeStampToFormatTime(this.time),
    )
}

fun NewTask.toTask(): Task {
    return Task(
        description = this.description,
        time = TimeFormat.formatDateTimeToTimeStamp(
            time = this.time,
            date = this.date
        ),
        isDone = false,
    )
}
