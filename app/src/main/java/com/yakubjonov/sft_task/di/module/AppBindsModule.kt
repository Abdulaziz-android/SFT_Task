package com.yakubjonov.sft_task.di.module

import androidx.lifecycle.ViewModel
import com.yakubjonov.sft_task.presentation.factory.ViewModelKey
import com.yakubjonov.sft_task.presentation.ui.basic.BasicViewModel
import com.yakubjonov.sft_task.presentation.ui.history.HistoryViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface AppBindsModule {

    @Binds
    @[IntoMap ViewModelKey(BasicViewModel::class)]
    fun provideBasicViewModel(basicViewModel: BasicViewModel) : ViewModel

    @Binds
    @[IntoMap ViewModelKey(HistoryViewModel::class)]
    fun provideHistoryViewModel(historyViewModel: HistoryViewModel) : ViewModel


}