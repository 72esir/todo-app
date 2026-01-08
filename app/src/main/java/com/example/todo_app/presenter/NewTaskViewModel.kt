package com.example.todo_app.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo_app.data.models.Task
import com.example.todo_app.data.models.TaskStatus
import com.example.todo_app.domain.CreateTaskUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewTaskViewModel @Inject constructor(
    private val createTaskUseCase: CreateTaskUseCase
): ViewModel() {
    private var deadline: Long? = null

    fun createTask(
        title: String?,
        description: String?,
    ): Boolean {
        val trimmedTitle = title?.trim()
        val trimmedDescription = description?.trim()
        val taskDeadline = deadline
        if (trimmedTitle.isNullOrEmpty() || trimmedDescription.isNullOrEmpty() || taskDeadline == null) {
            return false
        }
        viewModelScope.launch {
            createTaskUseCase(
                Task(
                    title = trimmedTitle,
                    description = trimmedDescription,
                    deadline = taskDeadline,
                    status = TaskStatus.TODO,
                )
            )
        }
        return true
    }

    fun saveDeadline(value: Long?) {
        deadline = value
    }

}
