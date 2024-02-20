package com.example.todo_list.view.pages.todo_list

import com.example.todo_list.domain.repository.TaskRepository
import com.example.todo_list.view.model.TaskUi
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class TodoListViewModelTest {
    private val taskRepository: TaskRepository = mockk()
    private lateinit var viewModel: TodoListViewModel

    private val tasks = listOf(
        TaskUi(
            1,
            "description",
            "156146146",
            "156146146",
            false
        ),
        TaskUi(
            2,
            "description 2",
            "156146142",
            "156146142",
            false
        )
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = TodoListViewModel(taskRepository)
    }

    @Test
    fun `is load successfully all tasks from database`() = runTest {
        // GIVEN
        coEvery { taskRepository.getAllTasks() } returns tasks
        // WHEN
        viewModel.handleUiEvent(TodoListUiEvent.OnLoadingUiData)
        // RECEIVE
        val state = viewModel.uiState.value
        state?.tasks?.let {
            Assert.assertTrue(it.isNotEmpty())
        }
    }

    @Test
    fun `all tasks load from database only one time`() = runTest {
        coEvery { taskRepository.getAllTasks() } returns tasks

        viewModel.handleUiEvent(TodoListUiEvent.OnLoadingUiData)

        coVerify(exactly = 1) {
            taskRepository.getAllTasks()
        }
    }


}

