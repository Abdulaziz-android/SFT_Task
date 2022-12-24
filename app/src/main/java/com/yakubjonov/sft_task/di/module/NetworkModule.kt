package com.yakubjonov.sft_task.di.module

import com.yakubjonov.data.remote.api.CardApi
import com.yakubjonov.domain.common.Constants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient : OkHttpClient.Builder):Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient.build())
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClientBuilder(): OkHttpClient.Builder {
        val okHttpClient = OkHttpClient().newBuilder()
        okHttpClient.apply {
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
        }
        return okHttpClient
    }

    @Provides
    fun provideCardApi(retrofit: Retrofit):CardApi{
        return retrofit.create(CardApi::class.java)
    }


}