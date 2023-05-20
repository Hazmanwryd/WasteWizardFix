package com.kelompok3.wastewizard.Api

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    val articles: List<Article>
)

data class Article(
    val title: String,
    val author: String,
    val description: String,
    val content: String,
    @SerializedName("urlToImage")
    val imageUrl: String
)

