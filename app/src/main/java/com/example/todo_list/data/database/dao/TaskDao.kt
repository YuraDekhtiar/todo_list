package com.example.todo_list.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.todo_list.data.database.entities.Task


@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks")
    suspend fun getAllTasks() : List<Task>

    @Query("SELECT * FROM tasks WHERE description LIKE (:search)")
    suspend fun searchTask(search: String) : List<Task>?

    @Query("SELECT * FROM tasks WHERE task_id IN (:id)")
    suspend fun getTaskById(id: Int) : Task?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)
}