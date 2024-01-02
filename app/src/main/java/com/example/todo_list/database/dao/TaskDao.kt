package com.example.todo_list.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.todo_list.database.entities.Task


@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks")
    suspend fun getAll() : List<Task>
}