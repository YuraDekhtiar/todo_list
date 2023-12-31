package com.example.todo_list.pages.new_task

import CalendarPickerDialog
import android.app.TimePickerDialog
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TimeInput
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
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
import com.example.todo_list.pages.widgets.BackButton
import com.example.todo_list.pages.widgets.IconTextButton
import com.example.todo_list.pages.widgets.SaveButton
import com.example.todo_list.ui.theme.Black40
import com.example.todo_list.ui.theme.Black50
import com.example.todo_list.ui.theme.White
import com.example.todo_list.ui.theme.Yellow100
import com.example.todo_list.ui.theme.YellowFocused
import java.util.Calendar

@Composable
fun NewTaskScreen(onClickBack: () -> Unit) {
    Scaffold(
        content = {
            NewTaskScreenContent(modifier = Modifier.padding(it))
        },
        topBar = {
            TopBar(
                onClickBack = onClickBack
            ) {
                Log.d("SaveButton", "Clicke Save Button")
            }
        }
    )
}

@Composable
private fun TopBar(onClickBack: () -> Unit, onClickSave: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BackButton(onClick = onClickBack)
        SaveButton(onClick = onClickSave)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NewTaskScreenContent(modifier: Modifier = Modifier) {

    Column(modifier = modifier) {
        val time = rememberSaveable { mutableStateOf("21:00") }
        val date = rememberSaveable { mutableStateOf("29/12/2023") }

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
                onClick = {}

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
                },
                onClickCancel = {
                    isShowDialogSelectDate.value = false
                }
            )
        }

        val state = rememberTimePickerState()


        TimePickerDialog(
            onCancel = { isShowDialogSelectTime.value = false },
            onConfirm = {
                val cal = Calendar.getInstance()
                cal.set(Calendar.HOUR_OF_DAY, state.hour)
                cal.set(Calendar.MINUTE, state.minute)
                cal.isLenient = false

                isShowDialogSelectTime.value = false
            },
        ) {
            TimeInput(state = state)
        }

        TaskDescription()

    }
}

@Composable
private fun TaskDescription() {
    var text by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        shape = RoundedCornerShape(16.dp),
        value = text,
        onValueChange = { text = it },
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