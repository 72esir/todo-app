package com.example.todo_app.di.viewModel

import android.app.Application
import com.example.todo_app.di.AppBindsModule
import com.example.todo_app.presenter.MainFragment
import com.example.todo_app.presenter.NewTaskFragment
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Component(
    modules = [AppModule::class]
)

@Singleton
abstract class AppComponent {
    abstract fun inject(fragment: MainFragment)
    abstract fun inject(fragment: NewTaskFragment)

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(app: Application): Builder
        fun build(): AppComponent
    }
}

@Module(
    includes = [
        AppBindsModule::class,
        ViewModelModule::class,
    ]
)
class AppModule