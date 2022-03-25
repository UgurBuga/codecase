package com.ugurbuga.codecase.domain

import com.ugurbuga.codecase.Resource
import com.ugurbuga.codecase.base.BaseUseCase
import com.ugurbuga.codecase.map
import com.ugurbuga.codecase.repository.product.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlinx.coroutines.flow.map

class GetProductDetailUseCase @Inject constructor(
    private val productRepository: ProductRepository,
    private val productMapper: ProductMapper
) : BaseUseCase<GetProductDetailUseCase.ProductDetailParams, ProductDetailUIModel>() {

    data class ProductDetailParams(val productId: String)

    override fun execute(params: ProductDetailParams): Flow<Resource<ProductDetailUIModel>> {
        return productRepository.getProductDetail(params.productId).map {
            it.map { productDetailResponse ->
                productMapper.toProductDetailUIModel(productDetailResponse)
            }
        }
    }
}