package com.example.todo_list.view.pages.new_task

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.todo_list.base.BaseViewModel
import com.example.todo_list.base.UiEvent
import com.example.todo_list.domain.repository.TaskRepository
import com.example.todo_list.view.mapper.toTask
import com.example.todo_list.view.model.NewTask
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewTaskViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : BaseViewModel<NewTaskUiState>() {
    override fun handleUiEvent(uiEvent: UiEvent) {
        when (uiEvent) {
            is NewTaskUiEvent.OnSaveClick -> {
                viewModelScope.launch {
                    uiState.value?.let {
                        taskRepository.createTask(it.newTask.toTask())
                    }
                }
            }

            is NewTaskUiEvent.OnBackClick -> {
                Log.d("back", "back")
                //onBackClickEvent.call()

            }

            is NewTaskUiEvent.OnLoadingUiData -> {
                viewModelScope.launch {
                    updateState {
                        it.value = NewTaskUiState(
                            newTask = NewTask.emptyTask()
                        )
                    }
                }
            }
        }
    }
}