package com.example.todo_list.domain.mapper

import com.example.todo_list.base.utils.TimeDateFormat
import com.example.todo_list.domain.model.TaskDomain
import com.example.todo_list.view.model.TaskUi

fun TaskDomain.toTaskUi(): TaskUi {
    return TaskUi(
        taskId = this.taskId,
        description = this.description,
        isDone = this.isDone,
        time = TimeDateFormat.timeStampToFormatTime(this.time),
        date = TimeDateFormat.timeStampToFormatDate(this.time)
    )
}



