package com.example.todo_list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.todo_list.navigation.AppNavigation
import com.example.todo_list.ui.theme.Todo_listTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Todo_listTheme {
                AppNavigation()
            }
        }
    }
}