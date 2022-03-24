package com.ugurbuga.turkcellcodecase.domain

import com.ugurbuga.turkcellcodecase.Resource
import com.ugurbuga.turkcellcodecase.base.BaseUseCase
import com.ugurbuga.turkcellcodecase.repository.product.ProductRepository
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