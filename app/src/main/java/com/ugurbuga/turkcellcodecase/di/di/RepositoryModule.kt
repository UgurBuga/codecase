package com.ugurbuga.turkcellcodecase.di.di

import com.ugurbuga.turkcellcodecase.repository.product.ProductRepository
import com.ugurbuga.turkcellcodecase.repository.product.ProductRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideProductRepository(
        productRepositoryImpl: ProductRepositoryImpl
    ): ProductRepository

}
