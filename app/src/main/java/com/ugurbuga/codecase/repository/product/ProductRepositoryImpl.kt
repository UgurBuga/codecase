package com.ugurbuga.codecase.repository.product

import com.ugurbuga.codecase.base.BaseRepository
import com.ugurbuga.codecase.common.Resource
import com.ugurbuga.codecase.dao.ProductDao
import com.ugurbuga.codecase.data.mapper.ProductEntityMapper
import com.ugurbuga.codecase.data.model.response.ProductDetailResponse
import com.ugurbuga.codecase.data.model.response.ProductListResponse
import com.ugurbuga.codecase.data.services.ProductService
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ProductRepositoryImpl @Inject constructor(
    private val productService: ProductService,
    private val productEntityMapper: ProductEntityMapper,
    private val productDao: ProductDao
) : ProductRepository, BaseRepository() {

    override fun getProductList(): Flow<Resource<ProductListResponse>> {
        return onOfflineSupportApiCall({
            productService.getList()
        }, {
            productDao.insertAll(productEntityMapper.toProductEntityList(it))
        }, {
            productEntityMapper.toProductListResponse(productDao.getProductList())
        })
    }

    override fun getProductDetail(productId: String): Flow<Resource<ProductDetailResponse>> {
        return onApiCall { productService.getDetail(productId) }
    }

}
