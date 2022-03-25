package com.ugurbuga.codecase.repository.product

import com.ugurbuga.codecase.Resource
import com.ugurbuga.codecase.base.BaseRepository
import com.ugurbuga.codecase.dao.ProductDao
import com.ugurbuga.codecase.data.services.ProductService
import com.ugurbuga.codecase.domain.ProductDetailResponse
import com.ugurbuga.codecase.domain.ProductListResponse
import com.ugurbuga.codecase.domain.ProductUIModel
import kotlinx.coroutines.flow.Flow
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
