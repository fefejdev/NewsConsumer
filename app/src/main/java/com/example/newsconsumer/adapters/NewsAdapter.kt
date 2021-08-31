package com.example.newsconsumer.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsconsumer.adapters.holders.NewsViewHolder
import com.example.newsconsumer.databinding.NewsItemBinding
import com.example.newsconsumer.models.Article

class NewsAdapter (private val dataSet : List<Article>, private val onClickItem: (Article) -> Unit)
    : RecyclerView.Adapter<NewsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = NewsItemBinding.inflate(LayoutInflater.from(parent.context))

        return NewsViewHolder(binding, parent.context,onClickItem)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}