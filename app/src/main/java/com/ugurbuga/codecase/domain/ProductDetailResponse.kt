package com.ugurbuga.codecase.domain

import com.google.gson.annotations.SerializedName

data class ProductDetailResponse(
    @SerializedName("description")
    val description: String?,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("product_id")
    val productId: String
)