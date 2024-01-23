package com.example.todo_list.view.pages.new_task

import androidx.lifecycle.ViewModel
import com.example.todo_list.base.BaseViewModel
import com.example.todo_list.base.UiEvent
import com.example.todo_list.domain.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewTaskViewModel @Inject constructor(private val taskRepository: TaskRepository) :
    BaseViewModel<NewTaskUiState>() {
    override fun handleUiEvent(uiEvent: UiEvent) {
        when(uiEvent) {
            is NewTaskUiEvent.OnSaveClick -> { }
            is NewTaskUiEvent.OnBackClick -> { }
            is NewTaskUiEvent.OnLoadingUiData -> { }
        }
    }

}