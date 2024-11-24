package com.effectivemobilett.mainscreen.model

import com.google.gson.annotations.SerializedName

data class CourseNetModel (
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("summary") val summary: String,
    @SerializedName("language") val language: String,
    @SerializedName("review_summary") val gradeId: Long,
    @SerializedName("target_audience") val targetAudience: String,
    @SerializedName("became_published_at") val publishedDate: String,
    @SerializedName("cover") val coverUrl: String? = null,
    @SerializedName("display_price") val price: String? = null,
    @SerializedName("difficulty") val difficulty: String? = null,
    @SerializedName("certificate_cover_org") val certificateCoverUrl: String? = null,
)

fun CourseNetModel.toDomain() = com.effectivemobilett.mainscreen.model.CourseModel(
    id = id,
    title = title,
    summary = summary,
    language = language,
    gradeId = gradeId,
    publishedDate = publishedDate,
    targetAudience = targetAudience,
    price = price,
    coverUrl = coverUrl,
    difficulty = difficulty,
    certificateCoverUrl = certificateCoverUrl
)

//fun List<CourseNetModel>.toDomain() = map(CourseNetModel::toDomain)
