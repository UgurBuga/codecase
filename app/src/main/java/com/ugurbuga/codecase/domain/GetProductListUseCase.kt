package com.ugurbuga.codecase.domain

import com.ugurbuga.codecase.Resource
import com.ugurbuga.codecase.base.BaseUseCase
import com.ugurbuga.codecase.repository.product.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductListUseCase @Inject constructor(
    private val productRepository: ProductRepository
) : BaseUseCase<GetProductListUseCase.ProductListParams, List<ProductUIModel>>() {

    data class ProductListParams(val id: String = "")

    override fun execute(params: ProductListParams): Flow<Resource<List<ProductUIModel>>> {
        return productRepository.getProductListFromLocale()
    }
}