package com.ugurbuga.turkcellcodecase.repository.product

import com.ugurbuga.turkcellcodecase.Resource
import com.ugurbuga.turkcellcodecase.base.BaseRepository
import com.ugurbuga.turkcellcodecase.data.services.ProductService
import com.ugurbuga.turkcellcodecase.domain.ProductDetailResponse
import com.ugurbuga.turkcellcodecase.domain.ProductListResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productService: ProductService
) : ProductRepository, BaseRepository() {

    override fun getProductList(): Flow<Resource<ProductListResponse>> {
        return onApiCall { productService.getList() }
    }

    override fun getProductDetail(productId: String): Flow<Resource<ProductDetailResponse>> {
        return onApiCall { productService.getDetail(productId) }
    }

}
