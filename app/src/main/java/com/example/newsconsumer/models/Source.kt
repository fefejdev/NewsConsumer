package com.example.newsconsumer.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Source(
    @SerializedName("id")
    @Expose
    val id: String? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null
) : Parcelable
