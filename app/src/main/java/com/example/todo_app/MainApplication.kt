package com.example.todo_app

import android.app.Application
import android.content.Context
import com.example.todo_app.di.viewModel.AppComponent
import com.example.todo_app.di.viewModel.DaggerAppComponent

class MainApplication: Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()

        super.onCreate()
    }
}

val Context.appComponent: AppComponent
    get() = when(this) {
        is MainApplication -> this.appComponent
        else -> applicationContext.appComponent
    }
