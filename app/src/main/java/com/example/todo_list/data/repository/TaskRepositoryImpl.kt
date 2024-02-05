package com.example.todo_list.data.repository

import com.example.todo_list.data.database.entities.Task
import com.example.todo_list.data.database.mapper.toDomain
import com.example.todo_list.domain.datasource.LocalDataSource
import com.example.todo_list.domain.mapper.toTaskUi
import com.example.todo_list.domain.model.TaskDomain
import com.example.todo_list.domain.repository.TaskRepository
import com.example.todo_list.view.model.TaskUi
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
) : TaskRepository {

    override suspend fun getAllTasks(): List<TaskUi> {
        return localDataSource.getAllTasks().map {
            it.toTaskUi()
        }
    }

    override suspend fun getTaskById(id: Int): TaskDomain? {
        return localDataSource.getTaskById(id)?.toDomain()
    }

    override suspend fun deleteTask(id: Int) {
        localDataSource.deleteTask(id)
    }

    override suspend fun createTask(task: Task) {
        localDataSource.saveTask(task)
    }

    override suspend fun editTask(task: Task) {
        localDataSource.updateTask(task)
    }

    override suspend fun changeTaskStatus(id: Int, isDone: Boolean) {
        localDataSource.changeTaskStatus(id, isDone)
    }

    override suspend fun searchTask(search: String): List<TaskUi> {
        return localDataSource.searchTask(search).map {
            it.toTaskUi()
        }
    }
}
