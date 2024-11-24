package com.effectivemobilett.mainscreen.model

import com.google.gson.annotations.SerializedName

data class CatalogNetModel(
    @SerializedName("content") val contentNetModel: List<ContentNetModel>
)
