package com.effectivemobilett.network

import com.effectivemobilett.mainscreen.model.GradeNetModel
import com.google.gson.annotations.SerializedName

data class GradeResponse(
    @SerializedName("course-review-summaries") val gradeList: List<GradeNetModel>
)