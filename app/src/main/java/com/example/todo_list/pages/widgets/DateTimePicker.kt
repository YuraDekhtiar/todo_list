import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.todo_list.R
import java.text.SimpleDateFormat
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarPickerDialog(
    onClickOk: (date: String) ->
    Unit, onClickCancel: () -> Unit
) {
    val datePickerState = rememberDatePickerState()
    DatePickerDialog(
        onDismissRequest = {
            onClickCancel()
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onClickCancel()
                }
            ) {
                Text(stringResource(id = R.string.cancel))
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    datePickerState.selectedDateMillis?.let {
                        onClickOk(
                            SimpleDateFormat("dd/MM/yyyy").format(Date(it))
                        )
                    }
                },
            ) {
                Text(stringResource(id = R.string.ok))
            }
        },
    ) {
        DatePicker(state = datePickerState)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialog1(
    onClickOk: (selectedHour: Int, selectedMinute: Int) -> Unit,
    onClickCancel: () -> Unit
) {
    val timePickerState = rememberTimePickerState(
        initialHour = 0,
        initialMinute = 0
    )
    AlertDialog(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(size = 12.dp)
            ),
        onDismissRequest = { onClickCancel() }
    ) {
        Column(
            modifier = Modifier
                .background(
                    color = Color.LightGray.copy(alpha = 0.3f)
                )
                .padding(top = 28.dp, start = 20.dp, end = 20.dp, bottom = 12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // time picker
            TimeInput(state = timePickerState)

            // buttons
            Row(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                // cancel button
                TextButton(onClick = { onClickCancel() }) {
                    Text(text = stringResource(id = R.string.cancel))
                }

                // confirm button
                TextButton(
                    onClick = {
                        onClickOk(
                            timePickerState.hour,
                            timePickerState.minute
                        )
                    }
                ) {
                    Text(text = stringResource(id = R.string.ok))
                }
            }
        }
    }
}