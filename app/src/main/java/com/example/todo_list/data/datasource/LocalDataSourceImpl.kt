package com.example.todo_list.data.datasource

import com.example.todo_list.data.database.AppDatabase
import com.example.todo_list.data.database.entities.Task
import com.example.todo_list.data.database.mapper.toDomain
import com.example.todo_list.domain.datasource.LocalDataSource
import com.example.todo_list.domain.model.TaskDomain
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val database: AppDatabase
) : LocalDataSource {

    override suspend fun getAllTasks(): List<TaskDomain> {
        return database.taskDao().getAllTasks().map {
            it.toDomain()
        }
    }

    override suspend fun getTaskById(id: Int): Task? {
        return database.taskDao().getTaskById(id)
    }

    override suspend fun saveTask(task: Task) {
        return database.taskDao().saveTask(task)
    }

    override suspend fun deleteTask(id: Int) {
        database.taskDao().deleteTask(id)
    }

    override suspend fun updateTask(task: Task) {
        return database.taskDao().updateTask(task)
    }

    override suspend fun changeTaskStatus(id: Int, isDone: Boolean) {
        database.taskDao().changeTaskStatus(id, isDone)
    }

    override suspend fun searchTask(search: String): List<TaskDomain> {
        return database.taskDao().searchTask(search).map {
            it.toDomain()
        }
    }
}
