package com.ugurbuga.codecase.repository.product

import com.ugurbuga.codecase.Resource
import com.ugurbuga.codecase.domain.ProductDetailResponse
import com.ugurbuga.codecase.domain.ProductListResponse
import com.ugurbuga.codecase.domain.ProductUIModel
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    fun getProductList(): Flow<Resource<ProductListResponse>>

    fun getProductDetail(productId: String): Flow<Resource<ProductDetailResponse>>

    fun getProductListFromLocale(): Flow<Resource<List<ProductUIModel>>>

    suspend fun insertList(list: List<ProductUIModel>)

    suspend fun insertProduct(product: ProductUIModel)
}
