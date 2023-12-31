package com.example.todo_list.pages.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.todo_list.R
import com.example.todo_list.ui.theme.Yellow100

@Composable
fun BackButton(onClick: () -> Unit) {
    BaseButton(
        imageVector = Icons.Filled.ArrowBack,
        contentDescription = stringResource(id = R.string.back_button),
        onClick = onClick
    )
}

@Composable
fun SaveButton(onClick: () -> Unit) {
    BaseButton(
        imageVector = Icons.Filled.Done,
        contentDescription = stringResource(id = R.string.back_button),
        onClick = onClick
    )
}

@Composable
private fun BaseButton(
    imageVector: ImageVector,
    contentDescription: String,
    onClick: () -> Unit,
) {
    IconButton(onClick = { onClick() }) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            tint = Yellow100
        )
    }
}

@Composable
fun IconTextButton(
    imageVector: ImageVector,
    text: String,
    contentDescription: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier.clickable { onClick() }
    ) {
        Icon(
            modifier = Modifier
                .padding(end = 4.dp)
                .height(24.dp)
                .width(24.dp),
            imageVector = imageVector,
            tint = Yellow100,
            contentDescription = contentDescription
        )
        Text(text = text)
    }
}