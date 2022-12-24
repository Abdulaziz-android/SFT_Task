package com.yakubjonov.domain.model

data class Card(
    var bin : Int?=0,
    val bank: Bank?,
    val brand: String? = "",
    val country: Country?,
    val number: Number?,
    val prepaid: Boolean?,
    val scheme: String? = "",
    val type: String? = "",
    var timestamp : Long = 0
)