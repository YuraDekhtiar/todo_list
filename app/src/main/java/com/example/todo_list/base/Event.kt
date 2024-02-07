package com.example.todo_list.base

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class Event<T: Any> {
    private lateinit var content: T
    private val isEvent: MutableLiveData<Boolean> = MutableLiveData(false)

    fun call(content: T) {
        this.content = content
        isEvent.value = true
    }

    fun observer(owner: LifecycleOwner, onClickAction: (p: T) -> Unit) {
        val observer = Observer<Boolean> {
            if (isEvent.hasActiveObservers() && it) {
                onClickAction(content)
                isEvent.value = false
            }
        }
        isEvent.observe(owner, observer)
    }
}
