package com.effectivemobilett.mainscreen.model

data class BaseCourseModel(
    val id: Long,
    val title: String,
    val summary: String,
    val language: String,
    val publishedDate: String,
    val targetAudience: String,
    val average: Double,
    val countOfReviews: Int,
    val readableDate: String,
    val price: String? = null,
    val coverUrl: String? = null,
    val difficulty: String? = null,
    val certificateCoverUrl: String? = null,
)
