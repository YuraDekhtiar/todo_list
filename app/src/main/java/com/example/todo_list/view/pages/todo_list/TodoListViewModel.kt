package com.example.todo_list.view.pages.todo_list

import androidx.lifecycle.viewModelScope
import com.example.todo_list.base.BaseViewModel
import com.example.todo_list.base.Event
import com.example.todo_list.base.UiEvent
import com.example.todo_list.domain.repository.TaskRepository
import com.example.todo_list.view.model.TaskUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : BaseViewModel<TodoListUiState>() {
    val onEditClickEvent = Event<Int>()
    val onAddClickEvent = Event<Unit>()


    override fun handleUiEvent(uiEvent: UiEvent) {
        when (uiEvent) {
            is TodoListUiEvent.OnCheckClick -> {
                onCheckClick(uiEvent.task)
            }

            is TodoListUiEvent.OnEditClick -> {
                onEditClickEvent.call(uiEvent.id)
            }

            is TodoListUiEvent.OnDeleteClick -> {
                onDeleteClick(uiEvent.id)
            }

            is TodoListUiEvent.OnTaskClick -> {}
            is TodoListUiEvent.OnAddClick -> {
                onAddClickEvent.call(Unit)
            }

            is TodoListUiEvent.OnSearchUpdate -> {
                onSearchUpdate(uiEvent.text)
            }

            is TodoListUiEvent.OnSearchClear -> {
                onSearchClear()
            }

            is TodoListUiEvent.OnLoadingUiData -> {
                onLoadData()
            }
        }
    }

    private fun onSearchClear() {
        loadAllTasks()
    }

    private fun onSearchUpdate(text: String) {
        if (text.isEmpty()) {
            loadAllTasks()
        } else {
            viewModelScope.launch {
                val tasks = taskRepository.searchTask(text)

                updateState {
                    it.value = it.value?.copy(tasks = tasks)
                }
            }
        }
    }

    private fun onDeleteClick(id: Int) {
        viewModelScope.launch {
            updateState {
                it.value = it.value?.copy(
                    tasks = it.value!!.tasks.filter { task -> task.taskId != id }
                )
            }
            taskRepository.deleteTask(id)
        }
    }

    private fun onCheckClick(task: TaskUi) {
        viewModelScope.launch {
            updateState { state ->
                val newTasks = state.value?.tasks?.map { taskUI ->
                    if (taskUI.taskId == task.taskId) {
                        taskUI.copy(isDone = !task.isDone)
                    } else {
                        taskUI
                    }
                }

                state.value = state.value?.copy(
                    tasks = newTasks.orEmpty()
                )
            }

            taskRepository.changeTaskStatus(task.taskId, task.isDone)
        }
    }

    private fun onLoadData() {
        loadAllTasks()
    }

    private fun loadAllTasks() {
        viewModelScope.launch {
            val tasks = taskRepository.getAllTasks()

            updateState {
                it.value = TodoListUiState(tasks)
            }
        }
    }
}