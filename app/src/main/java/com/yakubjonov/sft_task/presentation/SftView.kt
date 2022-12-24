package com.yakubjonov.sft_task.presentation

interface SftView {

    fun dialogShow(message:String = "Подождите")
    fun dialogHide()
    fun dialogShowError(messageId:Int)

}