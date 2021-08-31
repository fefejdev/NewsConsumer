package com.example.newsconsumer.repository

import com.example.newsconsumer.models.Article
import com.example.newsconsumer.network.SearchService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.awaitResponse

class SearchRepository : KoinComponent {
    private val searchService : SearchService by inject()

    suspend fun search(query: String) : List<Article>? {
        val response = searchService.search(query).awaitResponse()

        return if(response.isSuccessful){
            response.body()?.articles
        } else {
            null
        }
    }
}