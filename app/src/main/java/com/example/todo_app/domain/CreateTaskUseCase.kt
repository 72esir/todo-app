package com.example.todo_app.domain

import com.example.todo_app.data.TodoRepository
import com.example.todo_app.data.models.Task
import javax.inject.Inject

interface CreateTaskUseCase {
    suspend operator fun invoke(task: Task)
}

class CreateTaskUseCaseImpl @Inject constructor(
    private val repository: TodoRepository,
): CreateTaskUseCase{
    override suspend fun invoke(task: Task) {
        repository.createTask(task)
    }
}