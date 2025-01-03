package com.effectivemobilett.network

import com.effectivemobilett.mainscreen.model.CatalogNetModel
import com.google.gson.annotations.SerializedName

data class CatalogBlockResponse(
    @SerializedName("catalog-blocks") val catalogBlocks: List<CatalogNetModel>
)
