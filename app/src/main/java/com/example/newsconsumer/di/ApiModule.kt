package com.example.newsconsumer.di

import com.example.newsconsumer.Constants.BASE_URL
import com.example.newsconsumer.network.SearchService
import com.example.newsconsumer.network.interceptor.HeaderInterceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor())
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(SearchService::class.java) }

}