package com.example.psynationalexam.model

data class NewsItem(
    val id: String,
    val category: String,
    val title: String,
    val time: String,
    val views: Int,
    val imageRes: Int? = null
)