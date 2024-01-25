package com.example.todo_list.view.model

import com.example.todo_list.view.TimeFormat

data class NewTask(
    var description: String,
    var time: String,
    var date: String
) {

    companion object {
        fun emptyTask(): NewTask {
            return NewTask(
                description = "",
                time = TimeFormat.getCurrentTime(),
                date = TimeFormat.getDateNextDay()
            )
        }
    }

}