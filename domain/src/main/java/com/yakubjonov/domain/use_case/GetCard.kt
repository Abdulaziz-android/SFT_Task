package com.yakubjonov.domain.use_case

import android.util.Log
import com.yakubjonov.domain.R
import com.yakubjonov.domain.common.Resource
import com.yakubjonov.domain.model.Card
import com.yakubjonov.domain.repository.CardRepository
import com.yakubjonov.domain.util.NetworkHelper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCard(
    private val cardRepository: CardRepository,
    private val networkHelper: NetworkHelper,
) {

    suspend operator fun invoke(bin:Int) : Flow<Resource<Card>> = flow{
        try {
            emit(Resource.Loading())
            delay(500)
            if (networkHelper.isNetworkConnected()){
                val card = cardRepository.getCardRemote(bin)
                if (card!=null){
                    card.bin = bin
                    card.timestamp = System.currentTimeMillis()
                    cardRepository.insertCard(card)
                    emit(Resource.Success(card))
                }else{
                    emit(Resource.Error(R.string.not_found))
                }
            }else{
                val card = cardRepository.getCardLocale(bin)
                if (card!=null){
                    emit(Resource.Success(card))
                }else{
                    emit(Resource.Error(R.string.connection_error))
                }
            }
        }
        catch (e:Exception) {
            Log.d("GetCard", "invoke: ${e.message}")
            if (e.message == "HTTP 404 ") {
                emit(Resource.Error(R.string.not_found))
            } else {
                emit(Resource.Error(R.string.error))
            }
        }
    }

}