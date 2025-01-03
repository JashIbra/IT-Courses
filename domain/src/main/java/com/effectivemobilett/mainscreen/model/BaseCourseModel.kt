package com.effectivemobilett.mainscreen.model

data class BaseCourseModel(
    val id: Long,
    val title: String,
    val shortDesc: String,
    val language: String,
    val publishedDate: String,
    val targetAudience: String,
    val rating: Double,
    val reviews: Int,
    val price: String? = null,
    val coverUrl: String? = null,
    val difficulty: String? = null,
    val certificateCoverUrl: String? = null,
)
