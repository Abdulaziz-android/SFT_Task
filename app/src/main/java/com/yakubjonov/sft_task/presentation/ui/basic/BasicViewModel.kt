package com.yakubjonov.sft_task.presentation.ui.basic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yakubjonov.domain.common.Resource
import com.yakubjonov.domain.model.Card
import com.yakubjonov.domain.use_case.GetCard
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class BasicViewModel @Inject constructor(
    private val getCardByBin: GetCard
): ViewModel() {

    private var _card = MutableSharedFlow<Resource<Card>>()
    val card : SharedFlow<Resource<Card>> get() = _card.asSharedFlow()

    fun getCard(bin:Int){
        viewModelScope.launch {
            getCardByBin(bin).onEach {
                _card.emit(it)
            }.launchIn(this)
        }
    }

}