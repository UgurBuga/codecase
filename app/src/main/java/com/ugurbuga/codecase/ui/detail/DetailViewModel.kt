package com.ugurbuga.codecase.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ugurbuga.codecase.base.BaseViewModel
import com.ugurbuga.codecase.domain.GetProductDetailUseCase
import com.ugurbuga.codecase.domain.ProductDetailUIModel
import com.ugurbuga.codecase.domain.ProductUIModel
import com.ugurbuga.codecase.extensions.doOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getProductDetailUseCase: GetProductDetailUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    val productId = savedStateHandle["product_id"] ?: ""


    private val _detail = MutableLiveData<ProductDetailUIModel>()
    val detail: LiveData<ProductDetailUIModel> get() = _detail

    init {
        getProductDetail()
    }

    private fun getProductDetail() {
        getProductDetailUseCase(GetProductDetailUseCase.ProductDetailParams(productId))
            .doOnSuccess {
                _detail.value = it
            }
            .launchIn(viewModelScope)
    }

}