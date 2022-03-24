package com.ugurbuga.turkcellcodecase.repository.product

import com.ugurbuga.turkcellcodecase.Resource
import com.ugurbuga.turkcellcodecase.base.BaseRepository
import com.ugurbuga.turkcellcodecase.dao.ProductDao
import com.ugurbuga.turkcellcodecase.data.services.ProductService
import com.ugurbuga.turkcellcodecase.domain.ProductDetailResponse
import com.ugurbuga.turkcellcodecase.domain.ProductListResponse
import com.ugurbuga.turkcellcodecase.domain.ProductListUIModel
import com.ugurbuga.turkcellcodecase.domain.ProductUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productService: ProductService,
    private val productDao: ProductDao
) : ProductRepository, BaseRepository() {

    override fun getProductList(): Flow<Resource<ProductListResponse>> {
        return onApiCall { productService.getList() }
    }

    override fun getProductListFromLocale(): Flow<Resource<List<ProductUIModel>>> {
        return onRoomFlowCall(productDao.getProductList())
    }

    override suspend fun insertList(list: List<ProductUIModel>) {
        return productDao.insertAll(list)
    }

    override suspend fun insertProduct(product: ProductUIModel) {
        return productDao.insert(product)
    }

    override fun getProductDetail(productId: String): Flow<Resource<ProductDetailResponse>> {
        return onApiCall { productService.getDetail(productId) }
    }

}
