package com.example.todo_list.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todo_list.view.pages.new_task.NewTaskScreen
import com.example.todo_list.view.pages.new_task.NewTaskViewModel
import com.example.todo_list.view.pages.todo_list.TodoListScreen
import com.example.todo_list.view.pages.todo_list.TodoListViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.TodoList.route) {
        composable(Routes.TodoList.route) {
            val viewModel = hiltViewModel<TodoListViewModel>()

            TodoListScreen(
                viewModel = viewModel,
                onClickNewTask = {
                    navController.navigate(Routes.NewTask.route)
                }
            )
        }

        composable(Routes.NewTask.route) {
            val viewModel = hiltViewModel<NewTaskViewModel>()

            NewTaskScreen(
                viewModel = viewModel,
                onClickBack = {
                    navController.popBackStack()
                }
            )
        }
    }

}

