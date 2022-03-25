package com.ugurbuga.codecase.domain

import com.squareup.moshi.Json

data class ProductDetailResponse(
    @Json(name = "description")
    val description: String?,
    @Json(name = "image")
    val image: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "price")
    val price: Int,
    @Json(name = "product_id")
    val productId: String
)