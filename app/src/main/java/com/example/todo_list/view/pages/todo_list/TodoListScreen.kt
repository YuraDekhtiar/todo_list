package com.example.todo_list.view.pages.todo_list

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.todo_list.R
import com.example.todo_list.view.model.TaskUi
import com.example.todo_list.view.pages.todo_list.widgets.SearchTextField
import com.example.todo_list.view.theme.Black44
import com.example.todo_list.view.theme.Gray100
import com.example.todo_list.view.theme.White
import com.example.todo_list.view.theme.Yellow100

@Composable
fun TodoListScreen(
    viewModel: TodoListViewModel,
    onClickNewTask: () -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.handleUiEvent(
            TodoListUiEvent.OnLoadingUiData
        )
    }

    val uiState by viewModel.uiState
    val uiEvent = viewModel::handleUiEvent

    Scaffold(
        topBar = {
            SearchTextField(
                onClearClick = {
                    uiEvent(TodoListUiEvent.OnSearchClear)
                },
                searchAction = {
                    uiEvent(TodoListUiEvent.OnSearchUpdate(it))
                }
            )
        },
        content = { innerPadding ->
            TodoListScreenContent(
                modifier = Modifier.padding(innerPadding),
                uiState = uiState,
                uiEvent = uiEvent
            )
        },
        bottomBar = {
            BottomBar(onClickNewTask = onClickNewTask)
        }
    )
}

@Composable
private fun BottomBar(onClickNewTask: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        FloatingActionButton(
            shape = CircleShape,
            onClick = { onClickNewTask() },
            contentColor = White,
            containerColor = Yellow100,
        ) {
            Icon(Icons.Rounded.Add, null)
        }
    }
}


@Composable
private fun TodoListScreenContent(
    modifier: Modifier = Modifier,
    uiState: TodoListUiState?,
    uiEvent: (TodoListUiEvent) -> Unit
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier
                .padding(top = 8.dp),
            color = Black44,
            text = stringResource(id = R.string.today_task)
        )
        LazyColumn(
            contentPadding = PaddingValues(vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            uiState?.let {
                items(it.tasks.size) { index ->
                    TodoListItem(
                        task = it.tasks[index],
                        uiEvent = uiEvent
                    )
                }
            }
        }
    }
}

@Composable
private fun TodoListItem(
    task: TaskUi,
    uiEvent: (TodoListUiEvent) -> Unit,
) {
    val checkedState = remember { mutableStateOf(task.isDone) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .border(
                width = 2.dp,
                color = Gray100,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable {
                checkedState.value = !checkedState.value
                uiEvent(TodoListUiEvent.OnCheckClick(task.taskId, checkedState.value))
            }
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Checkbox(
                checked = checkedState.value,
                onCheckedChange = { checkedState.value = it },
                colors = CheckboxDefaults.colors(
                    uncheckedColor = Yellow100,
                    checkedColor = Yellow100
                )
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(top = 8.dp, bottom = 8.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = task.time,
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = task.description,
                    style = MaterialTheme.typography.bodyLarge,
                    textDecoration = if (checkedState.value) TextDecoration.LineThrough
                    else TextDecoration.None
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.End
            ) {
                // Change button
                IconButton(onClick = {

                }) {
                    Icon(
                        Icons.Rounded.Create,
                        null,
                        tint = Yellow100
                    )
                }
                // Delete button
                IconButton(onClick = {
                    uiEvent(TodoListUiEvent.OnDeleteClick(task.taskId))
                }) {
                    Icon(
                        Icons.Rounded.Delete,
                        null,
                        tint = Yellow100
                    )
                }
            }
        }
    }
}

