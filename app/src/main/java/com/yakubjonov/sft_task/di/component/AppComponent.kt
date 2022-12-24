package com.yakubjonov.sft_task.di.component

import android.app.Application
import android.content.Context
import com.yakubjonov.sft_task.di.module.*
import com.yakubjonov.sft_task.presentation.factory.MultiViewModelFactory
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppBindsModule::class, DatabaseModule::class, NetworkModule::class,
    RepositoryModule::class, UseCaseModule::class])
interface AppComponent {

    val factory: MultiViewModelFactory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

}