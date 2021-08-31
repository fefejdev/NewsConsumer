package com.example.newsconsumer.adapters.holders

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsconsumer.databinding.NewsItemBinding
import com.example.newsconsumer.models.Article

class NewsViewHolder (private val itemBinding: NewsItemBinding, private val context : Context,private val onClickItem: (Article) -> Unit)
    : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(article: Article){
        setupListener(article)
        Glide.with(context)
            .load(article.urlToImage)
            .into(itemBinding.articleImageView)
        itemBinding.articleTitleView.text = article.title
        itemBinding.articleDescriptionView.text = article.description
    }

    private fun setupListener(article: Article){
        itemBinding.root.setOnClickListener {
            onClickItem(article)
        }
    }
}