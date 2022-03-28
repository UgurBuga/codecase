package com.ugurbuga.codecase.data.model.response

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("product_id")
    val productId: String
)