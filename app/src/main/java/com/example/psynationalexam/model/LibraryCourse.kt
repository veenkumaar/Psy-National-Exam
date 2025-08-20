package com.example.psynationalexam.model

data class LibraryCourse(
    val id: String,
    val subject: String,
    val title: String,
    val progress: Float, // 0f..1f
    val progressText: String,
    val timeLeft: String,
    val imageRes: Int? = null,
    val isDiscuss: Boolean = false
)