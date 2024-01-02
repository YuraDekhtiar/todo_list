package com.example.todo_list.base

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<S: UiState> : ViewModel() {

    private var _uiState = mutableStateOf<S?>(null)
    val uiState: State<S?> = _uiState

    abstract fun handleUiEvent(uiEvent: UiEvent)
    fun updateState(block: (state: MutableState<S?>) -> Unit) {
        block.invoke(_uiState)
    }

}