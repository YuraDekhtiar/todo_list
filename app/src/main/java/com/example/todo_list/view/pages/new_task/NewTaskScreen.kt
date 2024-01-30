package com.example.todo_list.view.pages.new_task

import CalendarPickerDialog
import TimePickerDialog1
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.todo_list.R
import com.example.todo_list.view.pages.widgets.BackButton
import com.example.todo_list.view.pages.widgets.IconTextButton
import com.example.todo_list.view.pages.widgets.SaveButton
import com.example.todo_list.view.theme.Black40
import com.example.todo_list.view.theme.Black50
import com.example.todo_list.view.theme.White
import com.example.todo_list.view.theme.Yellow100
import com.example.todo_list.view.theme.YellowFocused

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewTaskScreen(
    viewModel: NewTaskViewModel,
    onClickBack: () -> Unit
) {
//    BackHandler {
//        viewModel.handleUiEvent(
//            NewTaskUiEvent.OnBackClick
//        )
//    }

    LaunchedEffect(key1 = Unit) {
        viewModel.handleUiEvent(
            NewTaskUiEvent.OnLoadingUiData()
        )
    }

//    onBackClickEvent.obsereve {
//        onClickBack()
//    }

    val uiState by viewModel.uiState
    val uiEvent = viewModel::handleUiEvent

    uiState?.let { state ->

        Scaffold(
            content = {
                NewTaskScreenContent(
                    uiState = state,
                    modifier = Modifier.padding(it),
                    onChangeDate = { date -> state.task.date = date },
                    onChangeDescription = { desc -> state.task.description = desc },
                    onChangeTime = { time -> state.task.time = time }
                )
            },
            topBar = {
                TopBar(uiEvent = uiEvent)
            }
        )
    }
}

@Composable
private fun TopBar(
    uiEvent: (NewTaskUiEvent) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BackButton(onClick = { uiEvent(NewTaskUiEvent.OnBackClick) })
        SaveButton(onClick = { uiEvent(NewTaskUiEvent.OnSaveClick) })
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun NewTaskScreenContent(
    uiState: NewTaskUiState,
    modifier: Modifier = Modifier,
    onChangeTime: (time: String) -> Unit,
    onChangeDate: (date: String) -> Unit,
    onChangeDescription: (description: String) -> Unit
) {
    Column(modifier = modifier) {
        val time = rememberSaveable { mutableStateOf(uiState.task.time) }
        val date = rememberSaveable { mutableStateOf(uiState.task.date) }

        val isShowDialogSelectTime = remember { mutableStateOf(false) }
        val isShowDialogSelectDate = remember { mutableStateOf(false) }

        Row(
            modifier = Modifier
                .padding(top = 8.dp, bottom = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconTextButton(
                imageVector = ImageVector.vectorResource(id = R.drawable.outline_access_time),
                text = time.value,
                contentDescription = stringResource(id = R.string.select_date_button),
                onClick = {
                    isShowDialogSelectTime.value = true
                }

            )

            IconTextButton(
                imageVector = Icons.Filled.DateRange,
                text = date.value,
                contentDescription = stringResource(id = R.string.select_date_button),
                onClick = { isShowDialogSelectDate.value = true }
            )
        }
        if (isShowDialogSelectDate.value) {
            CalendarPickerDialog(
                onClickOk = {
                    isShowDialogSelectDate.value = false
                    date.value = it
                    onChangeDate(date.value)
                },
                onClickCancel = {
                    isShowDialogSelectDate.value = false
                }
            )
        }

        if (isShowDialogSelectTime.value) {
            TimePickerDialog1(
                onClickOk = { selectedHour, selectedMinute ->
                    time.value = "${selectedHour}:$selectedMinute"
                    isShowDialogSelectTime.value = false
                    onChangeTime(time.value)
                },
                onClickCancel = {
                    isShowDialogSelectTime.value = false
                }
            )
        }

        TaskDescription(
            uiState.task.description,
            onTaskDescriptionChange = { onChangeDescription(it) }
        )
    }
}


@Composable
private fun TaskDescription(
    taskDescription: String,
    onTaskDescriptionChange: (taskDescription: String) -> Unit
) {
    var text by rememberSaveable { mutableStateOf(taskDescription) }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        shape = RoundedCornerShape(16.dp),
        value = text,
        onValueChange = {
            text = it
            onTaskDescriptionChange(it)
        },
        placeholder = {
            Text(
                text = stringResource(id = R.string.task_description),
                style = MaterialTheme.typography.bodyMedium,
            )
        },
        textStyle = MaterialTheme.typography.bodyMedium,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = White,
            focusedContainerColor = White,
            cursorColor = Black50,
            focusedIndicatorColor = Black50,
            unfocusedIndicatorColor = Black40,
            unfocusedLeadingIconColor = Yellow100,
            focusedLeadingIconColor = YellowFocused,
            unfocusedTrailingIconColor = Black40,
            focusedTrailingIconColor = Black50,
            unfocusedTextColor = Black40,
            focusedTextColor = Black50,
            unfocusedPlaceholderColor = Black40,
            focusedPlaceholderColor = Black50
        ),
    )
}