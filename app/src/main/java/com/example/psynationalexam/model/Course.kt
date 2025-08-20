package com.example.psynationalexam.model

data class Course(
    val id: String,
    val subject: String,
    val title: String,
    val lessons: Int,
    val progress: Float, // 0f..1f
    val duration: String,
    val imageRes: Int? = null
)
