package com.effectivemobilett.network

import com.effectivemobilett.mainscreen.model.CoursesListsNetModel
import com.google.gson.annotations.SerializedName

data class CoursesListResponse(
    @SerializedName("course-lists") val courseLists: List<CoursesListsNetModel>
)
