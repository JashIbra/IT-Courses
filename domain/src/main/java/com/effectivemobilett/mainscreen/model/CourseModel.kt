package com.effectivemobilett.mainscreen.model

data class CourseModel(
    val id: Long,
    val title: String,
    val summary: String,
    val language: String,
    val summaryGradeId: Long,
    val publishedDate: String,
    val targetAudience: String,
    val price: String? = null,
    val coverUrl: String? = null,
    val difficulty: String? = null,
    val certificateCoverUrl: String? = null,
)
