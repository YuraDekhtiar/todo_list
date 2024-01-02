package com.example.todo_list.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "tasks",
)
data class Task(
    @PrimaryKey
    @ColumnInfo(name = "task_id")
    val taskId: Int,
    val description: String,
    val time: Long,
    @ColumnInfo(name = "is_done")
    val isDone: Boolean
)

