package com.example.todo_app.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo_app.data.models.Task
import com.example.todo_app.data.models.TaskStatus
import com.example.todo_app.domain.GetTasksUseCase
import com.example.todo_app.domain.UpdateTaskUseCaseImpl
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getTasksUseCase: GetTasksUseCase,
    private val updateTaskStateUseCase: UpdateTaskUseCaseImpl
) : ViewModel() {
    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>>
        get() = _tasks

    init {
        viewModelScope.launch {
            val tasks = getTasksUseCase().collect{
                _tasks.postValue(it)
            }
        }
    }


    fun changeTaskState(task: Task, taskState: TaskStatus){
        viewModelScope.launch {
            updateTaskStateUseCase(
                task= task,
                taskState=taskState,
            )
        }
    }
}