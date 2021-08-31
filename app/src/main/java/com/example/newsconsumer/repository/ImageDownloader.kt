package com.example.newsconsumer.repository

import android.app.DownloadManager
import android.net.Uri
import android.os.Environment
import java.io.File

class ImageDownloader {
    fun downloadImage(url: String, downloadManager: DownloadManager){
        val directory = File(Environment.DIRECTORY_DOWNLOADS)

        val request = DownloadManager.Request(Uri.parse(url)).apply {
            setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false)
                .setTitle("ArticleImage")
                .setDescription("")
                .setDestinationInExternalPublicDir(
                    directory.toString(),
                    "ArticleImage")
        }
        downloadManager.enqueue(request)
    }
}