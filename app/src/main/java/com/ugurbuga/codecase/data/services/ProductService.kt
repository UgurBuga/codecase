package com.ugurbuga.codecase.data.services

import com.ugurbuga.codecase.data.model.response.ProductListResponse
import com.ugurbuga.codecase.data.model.response.ProductDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductService {

    @GET("cart/list")
    suspend fun getList(): ProductListResponse

    @GET("cart/{productId}/detail")
    suspend fun getDetail(@Path("productId") productId: String): ProductDetailResponse
}
