package com.example.todo_list.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class TaskDomain(
    val taskId: Int = -1,
    val description: String = "",
    val time: Long = -1L,
    val isDone: Boolean = false
)

