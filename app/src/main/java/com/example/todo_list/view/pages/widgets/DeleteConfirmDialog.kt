package com.example.todo_list.view.pages.widgets

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.example.todo_list.R

@Composable
fun DeleteConfirmDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String
) {
    AlertDialog(
        icon = {
            Icon(
                Icons.Rounded.Delete,
                contentDescription = stringResource(id = R.string.delete_icon)
            )
        },
        title = {
            Text(
                text = dialogTitle,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        },
        text = {
            Text(
                text = stringResource(id = R.string.delete_dialog_text),
                textAlign = TextAlign.Justify
            )
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            // Yes button
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(text = stringResource(id = R.string.yes))
            }
        },
        dismissButton = {
            // Cancel button
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(text = stringResource(id = R.string.cancel))
            }
        }
    )
}