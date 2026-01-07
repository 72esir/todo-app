package com.example.todo_app.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Task.TABLE)
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val deadline: Long,
    val status: TaskStatus,
){
    companion object{
        const val TABLE = "tasks"
    }
}

