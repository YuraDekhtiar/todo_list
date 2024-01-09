package com.example.todo_list.view.pages.todo_list

import androidx.lifecycle.viewModelScope
import com.example.todo_list.base.BaseViewModel
import com.example.todo_list.base.UiEvent
import com.example.todo_list.data.database.entities.Task
import com.example.todo_list.domain.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(private val taskRepository: TaskRepository) :
    BaseViewModel<TodoListUiState>() {

    override fun handleUiEvent(uiEvent: UiEvent) {
        when (uiEvent) {
            is TodoListUiEvent.OnCheckClick -> {
                updateState {
                    //    it.value = "1"
                }
            }

            is TodoListUiEvent.OnEditClick -> {}
            is TodoListUiEvent.OnDeleteClick -> {}
            is TodoListUiEvent.OnTaskClick -> {}
            is TodoListUiEvent.OnAddClick -> {}
            is TodoListUiEvent.OnSearchUpdate -> {}
            is TodoListUiEvent.OnSearchClear -> {}
            is TodoListUiEvent.OnLoadingUiData -> {
                viewModelScope.launch {
                    val tasks = taskRepository.getAllTasks().orEmpty()

                    updateState {
                        it.value = TodoListUiState(tasks)
                    }
                }
            }
        }
    }
}