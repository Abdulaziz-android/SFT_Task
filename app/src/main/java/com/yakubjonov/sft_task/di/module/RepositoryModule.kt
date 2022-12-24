package com.yakubjonov.sft_task.di.module

import com.yakubjonov.data.local.dao.CardDao
import com.yakubjonov.data.remote.api.CardApi
import com.yakubjonov.data.repository.CardRepositoryImpl
import com.yakubjonov.domain.repository.CardRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideCardRepository(
        cardApi: CardApi,
        cardDao: CardDao
    ):CardRepository {
        return CardRepositoryImpl(cardApi, cardDao)
    }

}