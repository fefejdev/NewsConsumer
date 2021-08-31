package com.example.newsconsumer.ui.article

import android.app.DownloadManager
import androidx.lifecycle.ViewModel
import com.example.newsconsumer.repository.ImageDownloader
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.java.KoinJavaComponent.inject

class NewsDetailViewModel : ViewModel(), KoinComponent {
    private val imageDownloader: ImageDownloader by inject()

    fun downloadImage(url: String?, downloadManager: DownloadManager) {
        if (url != null) {
            imageDownloader.downloadImage(url, downloadManager)
        }
    }

}