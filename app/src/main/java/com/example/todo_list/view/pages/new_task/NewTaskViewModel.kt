package com.example.todo_list.view.pages.new_task

import androidx.lifecycle.viewModelScope
import com.example.todo_list.base.BaseViewModel
import com.example.todo_list.base.UiEvent
import com.example.todo_list.domain.mapper.toTaskUi
import com.example.todo_list.domain.repository.TaskRepository
import com.example.todo_list.view.mapper.toTask
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
                        if(it.task.taskId <=  0) {
                            taskRepository.createTask(it.task.toTask())
                        } else {
                            taskRepository.editTask(it.task.toTask())
                        }
                    }
                }
            }

            is NewTaskUiEvent.OnBackClick -> {
                OnBackClickEvent.call()
            }

            is NewTaskUiEvent.OnLoadingUiData -> {
                viewModelScope.launch {
                    if (uiEvent.id <= 0) {
                        updateState {
                            it.value = NewTaskUiState()
                        }
                    } else {
                        val task = taskRepository.getTaskById(uiEvent.id)
                        updateState {
                            task?.let { t ->
                                it.value = NewTaskUiState(task = t.toTaskUi())
                            }
                        }
                    }
                }
            }
        }
    }
}
