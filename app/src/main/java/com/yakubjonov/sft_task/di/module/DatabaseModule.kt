package com.yakubjonov.sft_task.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.yakubjonov.data.local.AppDatabase
import com.yakubjonov.data.local.dao.CardDao
import com.yakubjonov.domain.util.NetworkHelper
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    fun provideAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app.applicationContext,
            AppDatabase::class.java,
            AppDatabase.DATABASE
        ).allowMainThreadQueries()
            .build()
    }

    @Provides
    fun provideCardDao(appDatabase: AppDatabase):CardDao{
        return appDatabase.cardDao()
    }

    @Provides
    fun provideNetworkHelper(context: Context):NetworkHelper{
        return NetworkHelper(context)
    }

}