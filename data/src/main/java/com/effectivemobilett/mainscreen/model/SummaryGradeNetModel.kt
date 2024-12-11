package com.effectivemobilett.mainscreen.model

import com.google.gson.annotations.SerializedName

data class SummaryGradeNetModel(
    @SerializedName("average") val average: Double,
    @SerializedName("count") val countOfReviews: Int
)

fun SummaryGradeNetModel.toDomain() = SummaryGradeModel(
    average = average,
    countOfReviews = countOfReviews
)
