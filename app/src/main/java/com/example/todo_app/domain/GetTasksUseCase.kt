package com.example.todo_app.domain

import com.example.todo_app.data.TodoRepository
import com.example.todo_app.data.models.Task
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetTasksUseCase {
    operator fun invoke(): Flow<List<Task>>
}

class GetTasksUseCaseImpl @Inject constructor(
    private val repository: TodoRepository
): GetTasksUseCase{
    override fun invoke(): Flow<List<Task>> = repository.getAllTasks()
}