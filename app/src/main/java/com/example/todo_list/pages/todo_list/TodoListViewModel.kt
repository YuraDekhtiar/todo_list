package com.example.todo_list.pages.todo_list

import com.example.todo_list.base.BaseViewModel
import com.example.todo_list.base.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor() : BaseViewModel<TodoListUiState>() {

    override fun handleUiEvent(uiEvent: UiEvent) {
        when (uiEvent) {
            is TodoListUiEvent.OnCheckClick  -> {
                updateState {
                //    it.value = "1"
                }
            }
            is TodoListUiEvent.OnEditClick -> { }
            is TodoListUiEvent.OnDeleteClick -> { }
            is TodoListUiEvent.OnTaskClick -> { }
            is TodoListUiEvent.OnAddClick -> { }
            is TodoListUiEvent.OnSearchUpdate -> { }
            is TodoListUiEvent.OnSearchClear -> { }
            is TodoListUiEvent.OnLoadingUiData -> {

            }
        }
    }
}