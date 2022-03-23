package com.ugurbuga.turkcellcodecase.domain


import com.squareup.moshi.Json

data class ProductListResponse(
    @Json(name = "products")
    val products: List<ProductResponse>
)