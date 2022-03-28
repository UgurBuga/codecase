package com.ugurbuga.codecase.domain.usecease

import com.ugurbuga.codecase.base.BaseUseCase
import com.ugurbuga.codecase.common.Resource
import com.ugurbuga.codecase.common.map
import com.ugurbuga.codecase.domain.mapper.ProductMapper
import com.ugurbuga.codecase.domain.model.ProductListUIModel
import com.ugurbuga.codecase.repository.product.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetProductListUseCase @Inject constructor(
    private val productRepository: ProductRepository,
    private val productMapper: ProductMapper
) : BaseUseCase<Unit, ProductListUIModel>() {

    override fun execute(params: Unit): Flow<Resource<ProductListUIModel>> {
        return productRepository.getProductList().map {
            it.map { productListResponse ->
                productMapper.toProductListUIModel(productListResponse)
            }
        }
    }

}