package com.ugurbuga.turkcellcodecase.domain

import com.ugurbuga.turkcellcodecase.Resource
import com.ugurbuga.turkcellcodecase.base.BaseUseCase
import com.ugurbuga.turkcellcodecase.map
import com.ugurbuga.turkcellcodecase.repository.product.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetProductListUseCase @Inject constructor(
    private val productRepository: ProductRepository,
    private val productMapper: ProductMapper
) : BaseUseCase<GetProductListUseCase.ProductListParams, ProductListUIModel>() {

    data class ProductListParams(val id: String = "")

    override fun execute(params: ProductListParams): Flow<Resource<ProductListUIModel>> {
        return productRepository.getProductList().map {
            it.map { productListResponse ->
                productMapper.toProductListUIModel(productListResponse)
            }
        }
    }
}