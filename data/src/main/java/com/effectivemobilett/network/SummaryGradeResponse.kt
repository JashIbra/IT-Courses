package com.effectivemobilett.network

import com.effectivemobilett.mainscreen.model.SummaryGradeNetModel
import com.google.gson.annotations.SerializedName

data class SummaryGradeResponse(
    @SerializedName("course-review-summaries") val summaryGradeList: List<SummaryGradeNetModel>
)
