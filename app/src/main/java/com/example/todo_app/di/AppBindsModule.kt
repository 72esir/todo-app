package com.example.todo_app.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.todo_app.data.TodoRepository
import com.example.todo_app.data.TodoRepositoryImpl
import com.example.todo_app.data.db.TodoDAO
import com.example.todo_app.data.db.TodoDB
import com.example.todo_app.domain.CreateTaskUseCase
import com.example.todo_app.domain.CreateTaskUseCaseImpl
import com.example.todo_app.domain.GetTasksUseCase
import com.example.todo_app.domain.GetTasksUseCaseImpl
import com.example.todo_app.domain.UpdateTaskUseCase
import com.example.todo_app.domain.UpdateTaskUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface AppBindsModule {
    @Binds
    @Singleton
    fun bindTasksRepository(impl: TodoRepositoryImpl): TodoRepository

    @Binds
    @Singleton
    fun bindGetTasksUseCase(impl: GetTasksUseCaseImpl): GetTasksUseCase

    @Binds
    @Singleton
    fun bindUpdateTasksStateUseCase(impl: UpdateTaskUseCaseImpl): UpdateTaskUseCase

    @Binds
    @Singleton
    fun bindCreateTaskUseCase(impl: CreateTaskUseCaseImpl): CreateTaskUseCase

    companion object{

        @Provides
        fun provideContext(
            app: Application
        ): Context =
            app.applicationContext

        @Provides
        @Singleton
        fun provideDb(
            context: Context,
        ): TodoDB =
            Room.databaseBuilder(
                context,
                TodoDB::class.java,
                "tasks.db"
            ).build()

        @Provides
        @Singleton
        fun provideDao(
            db: TodoDB
        ): TodoDAO =
            db.todoDAO
    }

}