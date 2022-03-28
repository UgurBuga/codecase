package com.ugurbuga.codecase.domain.usecease

import com.ugurbuga.codecase.base.BaseUseCase
import com.ugurbuga.codecase.common.Resource
import com.ugurbuga.codecase.common.map
import com.ugurbuga.codecase.domain.mapper.ProductMapper
import com.ugurbuga.codecase.domain.model.ProductDetailUIModel
import com.ugurbuga.codecase.repository.product.ProductRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
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