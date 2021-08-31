package com.example.newsconsumer.ui.article

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.example.newsconsumer.databinding.ActivityNewsDetailBinding
import com.example.newsconsumer.models.Article

class NewsDetailActivity : AppCompatActivity() {
    private var currentArticle: Article? = null

    private val newsDetailViewModel: NewsDetailViewModel by viewModels()

    private lateinit var newsDetailBinding: ActivityNewsDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsDetailBinding = ActivityNewsDetailBinding.inflate(layoutInflater)
        setContentView(newsDetailBinding.root)
        getBundleFromIntent()
        populateActivity()
        setOnClickListeners()
    }

    private fun populateActivity() {
        currentArticle?.let {
            newsDetailBinding.articleTitleTextView.text = it.title
            newsDetailBinding.articleDescriptionView.text = it.description
            newsDetailBinding.articleContentView.text = it.content
            Glide.with(applicationContext)
                .load(it.urlToImage)
                .into(newsDetailBinding.articleImageView)
        }
    }

    private fun setOnClickListeners() {
        newsDetailBinding.downloadImageImageView.setOnClickListener {
            currentArticle?.let {
                newsDetailViewModel.downloadImage(
                    it.urlToImage,
                    this.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                )
            }
        }

        newsDetailBinding.shareArticleImageView.setOnClickListener {
            startShare()
        }

        newsDetailBinding.goToLinkImageView.setOnClickListener {
            startBrowser()
        }
    }

    private fun startShare() {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_SUBJECT, "Sharing Article")
            putExtra(Intent.EXTRA_TEXT, currentArticle?.url)
            type = "text/plain"
        }

        startActivity(Intent.createChooser(sendIntent, "Share Article"))
    }

    private fun startBrowser() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(currentArticle?.url))

        startActivity(intent)
    }

    private fun getBundleFromIntent() {
        val bundle: Bundle? = intent.extras
        bundle?.let {
            currentArticle = bundle.getParcelable("ARTICLE")
        }
    }
}