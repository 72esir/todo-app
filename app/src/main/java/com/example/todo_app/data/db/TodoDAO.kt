package com.example.todo_app.data.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.todo_app.data.models.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDAO {
    @Upsert
    suspend fun upsertTask(task: Task)

    @Query("SELECT * FROM ${Task.TABLE} ORDER BY deadline ASC")
    fun getAllTasks(): Flow<List<Task>>
}