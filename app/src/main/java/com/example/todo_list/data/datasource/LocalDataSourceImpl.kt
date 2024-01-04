package com.example.todo_list.data.datasource

import com.example.todo_list.data.database.AppDatabase
import com.example.todo_list.data.database.entities.Task
import com.example.todo_list.domain.datasource.LocalDataSource
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val database: AppDatabase) : LocalDataSource {

    override suspend fun getAllTasks(): List<Task>? {
        return database.taskDao().getAllTasks()
    }

    override suspend fun getTaskById(id: Int): Task? {
        return database.taskDao().getTaskById(id)
    }

    override suspend fun saveTask(task: Task) {
        return database.taskDao().saveTask(task)
    }

    override suspend fun updateTask(task: Task) {
        return database.taskDao().updateTask(task)
    }

    override suspend fun searchTask(search: String): List<Task>? {
        return database.taskDao().searchTask(search)
    }
}
