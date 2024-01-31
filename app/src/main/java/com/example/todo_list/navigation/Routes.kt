package com.example.todo_list.navigation

sealed class Routes(val route: String) {
    data object TodoList : Routes("todo_list_screen")
    data object NewTask : Routes("new_task_screen")

    data object TaskId : Routes("task_id")
}