package com.ugurbuga.turkcellcodecase.repository.product

import com.ugurbuga.turkcellcodecase.Resource
import com.ugurbuga.turkcellcodecase.domain.ProductDetailResponse
import com.ugurbuga.turkcellcodecase.domain.ProductListResponse
import com.ugurbuga.turkcellcodecase.domain.ProductListUIModel
import com.ugurbuga.turkcellcodecase.domain.ProductUIModel
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    fun getProductList(): Flow<Resource<ProductListResponse>>

    fun getProductDetail(productId: String): Flow<Resource<ProductDetailResponse>>

    fun getProductListFromLocale(): Flow<Resource<List<ProductUIModel>>>

    suspend fun insertList(list: List<ProductUIModel>)

    suspend fun insertProduct(product: ProductUIModel)
}
