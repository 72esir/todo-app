package com.example.todo_app.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todo_app.data.models.Task

@Database(
    entities = [
        Task::class,
    ],
    version = 1
)

abstract class TodoDB: RoomDatabase() {
    abstract val todoDAO: TodoDAO
}