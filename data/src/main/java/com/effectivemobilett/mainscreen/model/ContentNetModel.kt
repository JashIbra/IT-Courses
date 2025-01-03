package com.effectivemobilett.mainscreen.model

import com.google.gson.annotations.SerializedName

data class ContentNetModel(
    @SerializedName("id") val id: Int,
    @SerializedName("courses") val courseIds: List<Int>,
)