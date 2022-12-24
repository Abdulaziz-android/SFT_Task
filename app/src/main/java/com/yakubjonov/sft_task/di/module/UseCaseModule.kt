package com.yakubjonov.sft_task.di.module

import com.yakubjonov.domain.repository.CardRepository
import com.yakubjonov.domain.use_case.GetCard
import com.yakubjonov.domain.use_case.GetHistoryCards
import com.yakubjonov.domain.util.NetworkHelper
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideGetCard(
        cardRepository: CardRepository,
        networkHelper: NetworkHelper)
    : GetCard {
        return GetCard(cardRepository, networkHelper)
    }


    @Provides
    fun provideGetHistoryCards(
        cardRepository: CardRepository
    ) : GetHistoryCards {
        return GetHistoryCards(cardRepository)
    }

}