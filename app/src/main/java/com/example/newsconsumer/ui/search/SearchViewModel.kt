package com.example.newsconsumer.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsconsumer.models.Article
import com.example.newsconsumer.repository.SearchRepository
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SearchViewModel : ViewModel(), KoinComponent {
    private val searchRepository: SearchRepository by inject()
    private val _searchQuery = MutableLiveData<String>()
    private val _searchResult = MutableLiveData<List<Article>>()
    val searchResult: LiveData<List<Article>>
        get() = _searchResult

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun handleRequest() {
        viewModelScope.launch {
            _searchResult.value = _searchQuery.value?.let { searchRepository.search(it) }
        }
    }
}