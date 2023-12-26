package com.example.todo_list.pages.new_task

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NewTaskScreen(onClick: () -> Unit) {
    Scaffold(
        content = {
            NewTaskScreenContent(onClick = onClick, modifier = Modifier.padding(it))
        },
        topBar = {
            Text(text = "Create new task screen")
        }
    )
}

@Composable
private fun NewTaskScreenContent(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Text(
        text = "create new task 1",
        modifier.clickable { onClick() }
    )

}