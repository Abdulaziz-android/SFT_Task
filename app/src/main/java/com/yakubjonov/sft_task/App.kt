package com.yakubjonov.sft_task

import android.app.Application
import com.yakubjonov.sft_task.di.component.AppComponent
import com.yakubjonov.sft_task.di.component.DaggerAppComponent

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .application(application = this)
            .context(context = applicationContext)
            .build()
    }

}