package com.effectivemobilett.mainscreen.model

import com.google.gson.annotations.SerializedName

data class GradeNetModel(
    @SerializedName("average") val average: Double,
    @SerializedName("count") val countOfReviews: Int
)

fun GradeNetModel.toDomain() = com.effectivemobilett.mainscreen.model.GradeModel(
    average = average,
    countOfReviews = countOfReviews
)
