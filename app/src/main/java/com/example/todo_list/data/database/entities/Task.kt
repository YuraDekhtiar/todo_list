package com.example.todo_list.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "tasks",
)
data class Task(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "task_id")
    val taskId: Int,
    val description: String,
    val time: Long,
    @ColumnInfo(name = "is_done")
    val isDone: Boolean
)

