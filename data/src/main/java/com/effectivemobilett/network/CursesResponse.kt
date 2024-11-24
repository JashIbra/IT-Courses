package com.effectivemobilett.network

import com.effectivemobilett.mainscreen.model.CourseNetModel
import com.google.gson.annotations.SerializedName

data class CursesResponse(
    @SerializedName("courses") val courses: List<CourseNetModel>
)
