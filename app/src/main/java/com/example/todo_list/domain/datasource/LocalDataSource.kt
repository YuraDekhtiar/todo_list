package com.example.todo_list.domain.datasource

import com.example.todo_list.data.database.entities.Task

interface LocalDataSource {

    suspend fun getAllTasks(): List<Task>?
    suspend fun getTaskById(id: Int): Task?
    suspend fun saveTask(task: Task)
    suspend fun deleteTask(id: Int)
    suspend fun updateTask(task: Task)
    suspend fun searchTask(search: String): List<Task>?

}