package com.example.todo_list.data.repository

import com.example.todo_list.data.database.entities.Task
import com.example.todo_list.domain.datasource.LocalDataSource
import com.example.todo_list.domain.repository.TaskRepository
import javax.inject.Inject

class TaskRepositoryImpl
@Inject constructor(private val localDataSource: LocalDataSource) : TaskRepository {

    override suspend fun getAllTasks(): List<Task>? {
        return localDataSource.getAllTasks()
    }

    override suspend fun getTaskById(id: Int): Task? {
        return localDataSource.getTaskById(id)
    }

    override suspend fun createTask(task: Task) {
        localDataSource.saveTask(task)
    }

    override suspend fun editTask(task: Task) {
        localDataSource.updateTask(task)
    }

    override suspend fun searchTask(search: String): List<Task>? {
        return localDataSource.searchTask(search)
    }
}
