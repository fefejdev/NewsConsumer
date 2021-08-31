package com.example.newsconsumer.network

import com.example.newsconsumer.models.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("everything")
    fun search(@Query("q") query: String) : Call<News>

}