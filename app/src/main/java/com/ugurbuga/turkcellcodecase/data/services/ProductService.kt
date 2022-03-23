package com.ugurbuga.turkcellcodecase.data.services

import com.ugurbuga.turkcellcodecase.domain.ProductListResponse
import com.ugurbuga.turkcellcodecase.domain.ProductDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductService {

    @GET("cart/list")
    suspend fun getList(): ProductListResponse

    @GET("cart/{productId}/detail")
    suspend fun getDetail(@Path("productId") productId: String): ProductDetailResponse
}
