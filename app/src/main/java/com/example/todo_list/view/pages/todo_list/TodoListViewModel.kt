package com.example.todo_list.view.pages.todo_list

import androidx.lifecycle.viewModelScope
import com.example.todo_list.base.BaseViewModel
import com.example.todo_list.base.UiEvent
import com.example.todo_list.domain.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : BaseViewModel<TodoListUiState>() {

    override fun handleUiEvent(uiEvent: UiEvent) {
        when (uiEvent) {
            is TodoListUiEvent.OnCheckClick -> {
                viewModelScope.launch {
                    taskRepository.changeTaskStatus(uiEvent.id, uiEvent.currentState)
                }
            }

            is TodoListUiEvent.OnEditClick -> {}
            is TodoListUiEvent.OnDeleteClick -> {
                viewModelScope.launch {
                    taskRepository.deleteTask(uiEvent.id)

                    updateState {
                        it.value = it.value?.copy(
                            tasks = it.value!!.tasks.filter { task -> task.taskId != uiEvent.id }
                        )
                    }
                }
            }

            is TodoListUiEvent.OnTaskClick -> {

            }

            is TodoListUiEvent.OnAddClick -> {

            }

            is TodoListUiEvent.OnSearchUpdate -> {
                viewModelScope.launch {
                    val tasks = taskRepository.searchTask(uiEvent.text)

                    updateState {
                        it.value = it.value?.copy(tasks = tasks)
                    }
                }

            }

            is TodoListUiEvent.OnSearchClear -> {
                viewModelScope.launch {
                    val tasks = taskRepository.getAllTasks()

                    updateState {
                        it.value = TodoListUiState(tasks)
                    }
                }
            }

            is TodoListUiEvent.OnLoadingUiData -> {
                viewModelScope.launch {
                    val tasks = taskRepository.getAllTasks()

                    updateState {
                        it.value = TodoListUiState(tasks)
                    }
                }
            }
        }
    }
}