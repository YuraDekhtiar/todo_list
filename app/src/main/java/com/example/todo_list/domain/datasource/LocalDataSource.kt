package com.example.todo_list.domain.datasource

import com.example.todo_list.data.database.entities.Task
import com.example.todo_list.domain.model.TaskDomain

interface LocalDataSource {

    suspend fun getAllTasks(): List<TaskDomain>
    suspend fun getTaskById(id: Int): Task?
    suspend fun saveTask(task: Task)
    suspend fun deleteTask(id: Int)
    suspend fun updateTask(task: Task)
    suspend fun changeTaskStatus(id: Int, isDone: Boolean)
    suspend fun searchTask(search: String): List<TaskDomain>

}