package com.effectivemobilett.mainscreen.model

import com.google.gson.annotations.SerializedName

data class CoursesListsNetModel(
    @SerializedName("courses") val courseIds: List<Long>,
)