package com.example.todo_list.view.pages.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.todo_list.R
import com.example.todo_list.view.model.TaskUi
import com.example.todo_list.view.theme.Yellow100

@Composable
fun DetailTaskDialog(
    task: TaskUi,
    onDismissRequest: () -> Unit,
) {
    AlertDialog(
        text = {
            Column {
                Row(
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconTextLabel(
                        imageVector = ImageVector.vectorResource(id = R.drawable.outline_access_time),
                        text = task.time,
                    )

                    IconTextLabel(
                        imageVector = Icons.Filled.DateRange,
                        text = task.date,
                    )
                }
                Row {
                    Text(
                        textAlign = TextAlign.Justify,
                        text = task.description
                    )
                }
            }
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            // OK button
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(text = stringResource(id = R.string.ok))
            }
        },
    )
}

@Composable
private fun IconTextLabel(
    imageVector: ImageVector,
    text: String
) {
    Row {
        Icon(
            modifier = Modifier
                .padding(end = 4.dp)
                .height(24.dp)
                .width(24.dp),
            imageVector = imageVector,
            tint = Yellow100,
            contentDescription = null
        )
        Text(text = text)
    }
}