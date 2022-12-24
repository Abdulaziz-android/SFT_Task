package com.yakubjonov.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yakubjonov.domain.model.Bank
import com.yakubjonov.domain.model.Card
import com.yakubjonov.domain.model.Country

@Entity
data class CardEntity(
    @PrimaryKey
    val bin: Int,
    val bank_city: String? = "",
    val bank_name: String? = "",
    val bank_phone: String? = "",
    val bank_url: String? = "",
    val brand: String? = "",
    val country_alpha2: String? = "",
    val country_currency: String? = "",
    val country_emoji: String? = "",
    val country_latitude: String? = "",
    val country_longitude: String? = "",
    val country_name: String? = "",
    val country_numeric: String? = "",
    val number_length: Int?,
    val number_luhn: Boolean?,
    val prepaid: Boolean?,
    val scheme: String? = "",
    val type: String? = ""
)

fun CardEntity.toCard(): Card {
    return Card(
        bin = bin,
        bank = Bank(
            city = bank_city,
            name = bank_name,
            phone = bank_phone,
            url = bank_url
        ),
        brand = brand,
        country = Country(
            alpha2 = country_alpha2,
            currency = country_currency,
            emoji = country_emoji,
            latitude = country_latitude,
            longitude = country_longitude,
            name = country_name,
            numeric = country_numeric
        ),
        number = com.yakubjonov.domain.model.Number(
            length = number_length,
            luhn = number_luhn
        ),
        prepaid = prepaid,
        scheme = scheme,
        type = type
    )
}

fun Card.toCardEntity(): CardEntity {
    return CardEntity(
        bin = bin!!,
        bank_city = bank?.city,
        bank_name = bank?.name,
        bank_phone = bank?.phone,
        bank_url = bank?.url,
        brand = brand,
        country_alpha2 = country?.alpha2,
        country_currency = country?.currency,
        country_emoji = country?.emoji,
        country_latitude = country?.latitude,
        country_longitude = country?.longitude,
        country_name = country?.name,
        country_numeric = country?.numeric,
        number_length = number?.length,
        number_luhn = number?.luhn,
        prepaid = prepaid,
        scheme = scheme,
        type = type
    )
}