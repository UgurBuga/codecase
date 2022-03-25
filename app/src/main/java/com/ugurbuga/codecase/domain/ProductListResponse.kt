package com.ugurbuga.codecase.domain


import com.google.gson.annotations.SerializedName

data class ProductListResponse(
    @SerializedName("products")
    val products: List<ProductResponse>
)