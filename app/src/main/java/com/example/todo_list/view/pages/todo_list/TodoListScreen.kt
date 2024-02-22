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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults.elevation
import androidx.compose.material3.Icon
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
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.todo_list.R
import com.example.todo_list.view.model.TaskUi
import com.example.todo_list.view.pages.todo_list.widgets.SearchTextField
import com.example.todo_list.view.pages.widgets.DeleteConfirmDialog
import com.example.todo_list.view.pages.widgets.DeleteIconButton
import com.example.todo_list.view.pages.widgets.DetailTaskDialog
import com.example.todo_list.view.pages.widgets.EditIconButton
import com.example.todo_list.view.theme.Black44
import com.example.todo_list.view.theme.Gray100
import com.example.todo_list.view.theme.White
import com.example.todo_list.view.theme.Yellow100

@Composable
fun TodoListScreen(
    viewModel: TodoListViewModel,
    onClickNewTask: () -> Unit,
    onClickEditTask: (id: Int) -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.handleUiEvent(
            TodoListUiEvent.OnLoadingUiData
        )
    }

    viewModel.onEditClickEvent.observer(LocalLifecycleOwner.current) {
        onClickEditTask(it)
    }

    viewModel.onAddClickEvent.observer(LocalLifecycleOwner.current) {
        onClickNewTask()
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
            BottomBar(uiEvent = uiEvent)
        }
    )
}

@Composable
private fun BottomBar(uiEvent: (TodoListUiEvent) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        FloatingActionButton(
            shape = CircleShape,
            elevation = elevation(0.dp, 2.dp),
            onClick = {
                uiEvent(TodoListUiEvent.OnAddClick)
            },
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
    Column(
        modifier = modifier
    ) {
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
    val isShowDeleteConfirmDialog = remember { mutableStateOf(false) }
    val isShowDetailDialog = remember { mutableStateOf(false) }

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
                isShowDetailDialog.value = true
            }
    ) {
        // Checkbox
        Column(
            modifier = Modifier
                .width(40.dp)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Checkbox(
                checked = task.isDone,
                onCheckedChange = {
                    uiEvent(TodoListUiEvent.OnCheckClick(task))
                },
                colors = CheckboxDefaults.colors(
                    uncheckedColor = Yellow100,
                    checkedColor = Yellow100
                )
            )
        }
        // Content
        Column(
            modifier = Modifier
                .weight(1f)
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
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textDecoration = if (task.isDone) TextDecoration.LineThrough
                else TextDecoration.None
            )
        }
        // Icons
        Column(
            modifier = Modifier
                .width(40.dp)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            EditIconButton {
                uiEvent(TodoListUiEvent.OnEditClick(task.taskId))
            }
            DeleteIconButton {
                isShowDeleteConfirmDialog.value = true
            }

            if (isShowDeleteConfirmDialog.value) {
                DeleteConfirmDialog(
                    onDismissRequest = { isShowDeleteConfirmDialog.value = false },
                    onConfirmation = {
                        uiEvent(TodoListUiEvent.OnDeleteClick(task.taskId))
                        isShowDeleteConfirmDialog.value = false
                    },
                    dialogTitle = task.description,
                )
            }

            if (isShowDetailDialog.value) {
                DetailTaskDialog(
                    task = task,
                    onDismissRequest = { isShowDetailDialog.value = false }
                )
            }
        }

    }
}

