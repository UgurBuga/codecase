package com.ugurbuga.codecase.domain

import com.ugurbuga.codecase.Resource
import com.ugurbuga.codecase.base.BaseUseCase
import com.ugurbuga.codecase.map
import com.ugurbuga.codecase.repository.product.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SetProductListUseCase @Inject constructor(
    private val productRepository: ProductRepository,
    private val productMapper: ProductMapper
) : BaseUseCase<SetProductListUseCase.ProductListParams, ProductListUIModel>() {

    data class ProductListParams(val id: String = "")

    override fun execute(params: ProductListParams): Flow<Resource<ProductListUIModel>> {
        return productRepository.getProductList().map {
            it.map { productListResponse ->
                val response = productMapper.toProductListUIModel(productListResponse)
                productRepository.insertList(response.products)
                response
            }
        }
    }
}