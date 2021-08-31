package com.example.newsconsumer.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

data class News (
    @SerializedName("status")
    @Expose
    var status: String? = null,

    @SerializedName("totalResults")
    @Expose
    var totalResults: Int? = null,

    @SerializedName("articles")
    @Expose
    var articles: List<Article>? = null
    )