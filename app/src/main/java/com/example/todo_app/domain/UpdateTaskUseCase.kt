package com.example.todo_app.domain

import com.example.todo_app.data.TodoRepository
import com.example.todo_app.data.models.Task
import com.example.todo_app.data.models.TaskStatus
import javax.inject.Inject

interface UpdateTaskUseCase {
    suspend operator fun invoke(
        task: Task,
        taskState: TaskStatus
    )
}

class UpdateTaskUseCaseImpl @Inject constructor(
    private val repository: TodoRepository
): UpdateTaskUseCase{
    override suspend fun invoke(
        task: Task,
        taskState: TaskStatus
    ) = repository.updateTaskStatus(task,taskState)

}