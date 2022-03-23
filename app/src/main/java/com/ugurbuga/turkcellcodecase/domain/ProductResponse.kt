package com.ugurbuga.turkcellcodecase.domain

import com.squareup.moshi.Json

data class ProductResponse(
    @Json(name = "image")
    val image: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "price")
    val price: Int,
    @Json(name = "product_id")
    val productId: String
)