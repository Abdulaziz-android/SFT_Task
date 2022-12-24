package com.yakubjonov.domain.repository

import com.yakubjonov.domain.model.Card

interface CardRepository {

    suspend fun getCardRemote(bin:Int) : Card?

    suspend fun insertCard(card: Card)
    suspend fun getCardLocale(bin:Int) : Card?

    suspend fun getHistoryCards():List<Card>

}