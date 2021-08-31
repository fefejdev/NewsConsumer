package com.example.newsconsumer.network.interceptor

import com.example.newsconsumer.Constants.API_SECRET
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        requestBuilder.header("X-Api-Key", API_SECRET)

        return chain.proceed(requestBuilder.build())
    }
}
