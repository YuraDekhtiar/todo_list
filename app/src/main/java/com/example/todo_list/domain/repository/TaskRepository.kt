package com.example.todo_list.domain.repository

import com.example.todo_list.data.database.entities.Task
import com.example.todo_list.view.model.TaskUi

interface TaskRepository {

    suspend fun getAllTasks(): List<TaskUi>
    suspend fun getTaskById(id: Int): Task?
    suspend fun deleteTask(id: Int)
    suspend fun createTask(task: Task)
    suspend fun editTask(task: Task)
    suspend fun changeTaskStatus(id: Int, isDone: Boolean)
    suspend fun searchTask(search: String): List<TaskUi>
}