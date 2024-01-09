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
    val taskId: Int? = null,
    val description: String? = null,
    val time: Long? = null,
    @ColumnInfo(name = "is_done")
    val isDone: Boolean? = null
)

