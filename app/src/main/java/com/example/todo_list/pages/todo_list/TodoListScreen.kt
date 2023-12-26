package com.example.todo_list.pages.todo_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TodoListScreen(onClick: () -> Unit) {
    Scaffold(
        content = {
            TodoListScreenContent(onClick = onClick, modifier = Modifier.padding(it))
        },
        topBar = {
            Text(text = "Todo list Screen")
        }
    )
}

@Composable
private fun TodoListScreenContent(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Text(
        text = "create new task",
        modifier.clickable { onClick() }
    )

}