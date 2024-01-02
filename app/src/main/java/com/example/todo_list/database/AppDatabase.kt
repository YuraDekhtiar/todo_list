package com.example.todo_list.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todo_list.database.dao.TaskDao
import com.example.todo_list.database.entities.Task

@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun taskDao() : TaskDao

}