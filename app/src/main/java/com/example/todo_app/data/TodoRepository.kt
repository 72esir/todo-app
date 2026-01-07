package com.example.todo_app.data

import com.example.todo_app.data.db.TodoDAO
import com.example.todo_app.data.models.Task
import com.example.todo_app.data.models.TaskStatus
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface TodoRepository{
    fun getAllTasks(): Flow<List<Task>>
    suspend fun updateTaskStatus(task: Task, taskStatus: TaskStatus)
    suspend fun createTask(task: Task)
}

class TodoRepositoryImpl @Inject constructor(
    private val dao: TodoDAO,
): TodoRepository{
    override fun getAllTasks(): Flow<List<Task>> {
        return dao.getAllTasks()
    }

    override suspend fun updateTaskStatus(
        task: Task,
        taskStatus: TaskStatus
    ) {
        dao.upsertTask(task.copy(status = taskStatus))
    }

    override suspend fun createTask(task: Task) {
        dao.upsertTask(task)
    }

}