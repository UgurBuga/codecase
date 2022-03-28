package com.ugurbuga.codecase.repository.product

import com.ugurbuga.codecase.common.Resource
import com.ugurbuga.codecase.data.model.response.ProductDetailResponse
import com.ugurbuga.codecase.data.model.response.ProductListResponse
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    fun getProductList(): Flow<Resource<ProductListResponse>>

    fun getProductDetail(productId: String): Flow<Resource<ProductDetailResponse>>

}
