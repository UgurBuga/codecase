package com.ugurbuga.codecase.domain

import javax.inject.Inject

class ProductMapper @Inject constructor() {

    fun toProductListUIModel(response: ProductListResponse): ProductListUIModel {

        return ProductListUIModel(
            products = response.products.map { toProductUIModel(it) },
        )
    }

    fun toProductUIModel(response: ProductResponse): ProductUIModel {
        return ProductUIModel(
            image = response.image,
            name = response.name,
            price = response.price.toString(),
            productId = response.productId,
        )
    }
}
