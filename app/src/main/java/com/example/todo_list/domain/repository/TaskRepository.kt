package com.example.todo_list.domain.repository

import com.example.todo_list.data.database.entities.Task

interface TaskRepository {

    suspend fun getAllTasks(): List<Task>?
    suspend fun getTaskById(id: Int): Task?
    suspend fun createTask(task: Task)
    suspend fun editTask(task: Task)
    suspend fun searchTask(search: String): List<Task>?
}