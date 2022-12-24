package com.yakubjonov.sft_task.presentation.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yakubjonov.domain.common.Resource
import com.yakubjonov.domain.model.Card
import com.yakubjonov.domain.use_case.GetHistoryCards
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class HistoryViewModel @Inject constructor(
    private val getHistoryCards: GetHistoryCards
): ViewModel() {

    private var _cards = MutableSharedFlow<Resource<List<Card>>>()
    val cards : SharedFlow<Resource<List<Card>>> get() = _cards.asSharedFlow()

    fun getCards(){
        viewModelScope.launch {
            getHistoryCards().onEach{
                _cards.emit(it)
            }.launchIn(this)
        }
    }

}