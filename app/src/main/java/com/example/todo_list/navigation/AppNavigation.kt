package com.example.todo_list.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todo_list.pages.new_task.NewTaskScreen
import com.example.todo_list.pages.todo_list.TodoListScreen

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.TodoList.route) {
        composable(Routes.TodoList.route) {
            TodoListScreen(
                onClick = {
                    navController.navigate(Routes.NewTask.route)
                }

            )
        }

        composable(Routes.NewTask.route) {
            NewTaskScreen(onClick = {
                navController.popBackStack()
            })
        }
    }

}

