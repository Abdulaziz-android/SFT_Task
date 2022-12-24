package com.yakubjonov.domain.use_case

import android.util.Log
import com.yakubjonov.domain.R
import com.yakubjonov.domain.common.Resource
import com.yakubjonov.domain.model.Card
import com.yakubjonov.domain.repository.CardRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetHistoryCards(
    private val cardRepository: CardRepository,
) {

    private val TAG = "GetHistoryCards"
    suspend operator fun invoke() : Flow<Resource<List<Card>>> = flow{
        try {
            Log.d(TAG, "invoke: loading")
            emit(Resource.Loading())
            delay(500)
            val cards = cardRepository.getHistoryCards()
            if (cards.isNotEmpty()){
                Log.d(TAG, "invoke: success")
                emit(Resource.Success(cards))
            }else{
                emit(Resource.Error(R.string.not_found))
            }
        }
        catch (e:Exception) {
            emit(Resource.Error(R.string.error))
        }
    }

}