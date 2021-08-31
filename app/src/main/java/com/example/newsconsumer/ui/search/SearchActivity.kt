package com.example.newsconsumer.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.newsconsumer.R
import com.example.newsconsumer.adapters.NewsAdapter
import com.example.newsconsumer.databinding.ActivitySearchBinding
import com.example.newsconsumer.models.Article
import com.example.newsconsumer.ui.article.NewsDetailActivity

class SearchActivity : AppCompatActivity() {

    private val viewModel: SearchViewModel by viewModels()

    private lateinit var searchBinding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        searchBinding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(searchBinding.root)
        setupObservers()
        setupSwipeToRefresh()
        super.onCreate(savedInstanceState)
    }

    private fun setupObservers() {
        viewModel.searchResult.observe(this) {
            if (searchBinding.swapRefreshLayout.isRefreshing) {
                searchBinding.swapRefreshLayout.isRefreshing = false
            }
            it?.let {
                searchBinding.searchResultRecyclerView.adapter =
                    NewsAdapter(it) { article -> navigateToDetailScreen(article) }
            }
        }
    }

    private fun setupSwipeToRefresh() {
        searchBinding.swapRefreshLayout.setOnRefreshListener {
            viewModel.handleRequest()
        }
    }

    private fun navigateToDetailScreen(article: Article) {
        val intent = Intent(this, NewsDetailActivity::class.java)
        intent.putExtra("ARTICLE", article)
        startActivity(intent)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        with(menu?.findItem(R.id.searchMenu)?.actionView as SearchView) {
            this.queryHint = "Pesquisar"

            this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        viewModel.setSearchQuery(it)
                        viewModel.handleRequest()
                        searchBinding.swapRefreshLayout.isRefreshing = true
                        return true
                    }
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }

            })
        }
        return super.onCreateOptionsMenu(menu)
    }
}