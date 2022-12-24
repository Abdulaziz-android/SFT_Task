package com.yakubjonov.data.repository

import com.yakubjonov.data.local.dao.CardDao
import com.yakubjonov.data.local.entity.toCard
import com.yakubjonov.data.local.entity.toCardEntity
import com.yakubjonov.data.remote.api.CardApi
import com.yakubjonov.domain.model.Card
import com.yakubjonov.domain.repository.CardRepository

class CardRepositoryImpl(
    private val api: CardApi,
    private val dao: CardDao
    ) : CardRepository {

    override suspend fun getCardRemote(bin: Int): Card? {
        return api.getCardInfo(bin)
    }

    override suspend fun insertCard(card: Card) {
        dao.insert(card.toCardEntity())
    }

    override suspend fun getCardLocale(bin: Int): Card? {
        return dao.getCardByBin(bin)?.toCard()
    }

    override suspend fun getHistoryCards(): List<Card> {
        val entities = dao.getAllCards()
        val list = arrayListOf<Card>()
        entities.forEach {
            list.add(it.toCard())
        }
        list.sortByDescending { it.timestamp }
        return list
    }

}