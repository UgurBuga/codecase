package com.ugurbuga.codecase.data.mapper

import com.ugurbuga.codecase.data.entity.ProductEntity
import com.ugurbuga.codecase.data.model.response.ProductListResponse
import com.ugurbuga.codecase.data.model.response.ProductResponse
import javax.inject.Inject

class ProductEntityMapper @Inject constructor() {

    fun toProductEntityList(response: ProductListResponse): List<ProductEntity> {
        return response.products.map { toProductEntity(it) }
    }

    private fun toProductEntity(response: ProductResponse): ProductEntity {
        return ProductEntity(
            image = response.image,
            name = response.name,
            price = response.price.toString(),
            productId = response.productId
        )
    }

    fun toProductListResponse(response: List<ProductEntity>): ProductListResponse {

        return ProductListResponse(
            response.map { toProductResponse(it) }
        )
    }

    private fun toProductResponse(response: ProductEntity): ProductResponse {

        return ProductResponse(
            image = response.image,
            name = response.name,
            price = response.price.toInt(),
            productId = response.productId
        )
    }
}
