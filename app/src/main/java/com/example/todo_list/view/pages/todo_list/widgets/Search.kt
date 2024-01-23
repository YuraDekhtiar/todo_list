package com.example.todo_list.view.pages.todo_list.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.todo_list.R
import com.example.todo_list.view.theme.Black40
import com.example.todo_list.view.theme.Black50
import com.example.todo_list.view.theme.White
import com.example.todo_list.view.theme.Yellow100
import com.example.todo_list.view.theme.YellowFocused

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    searchAction: (searchText: String) -> Unit,
    onClearClick: () -> Unit
) {
    var searchText by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        textStyle = MaterialTheme.typography.bodySmall,
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
        placeholder = {
            Text(
                text = stringResource(R.string.search),
                style = MaterialTheme.typography.bodySmall
            )
                      },
        value = searchText,
        onValueChange = {
            searchText = it
            searchAction(searchText)
        },
        shape = RoundedCornerShape(24.dp),
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
            )
        },
        // Clear button
        trailingIcon = {
            Icon(
                imageVector = Icons.Outlined.Close,
                contentDescription = null,
                Modifier.clickable {
                    searchText = ""
                    onClearClick()
                }
            )
        }

    )
}