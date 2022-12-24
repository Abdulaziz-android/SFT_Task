package com.yakubjonov.data.remote.api

import com.yakubjonov.domain.model.Card
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface CardApi {

    @GET("/{bin}")
    suspend fun getCardInfo(
       @Path("bin") bin : Int
    ) : Card?

}