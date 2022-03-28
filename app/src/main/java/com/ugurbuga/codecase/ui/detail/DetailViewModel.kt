package com.ugurbuga.codecase.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ugurbuga.codecase.R
import com.ugurbuga.codecase.base.BaseViewEvent
import com.ugurbuga.codecase.base.BaseViewModel
import com.ugurbuga.codecase.domain.usecease.GetProductDetailUseCase
import com.ugurbuga.codecase.domain.model.ProductDetailUIModel
import com.ugurbuga.codecase.extensions.doOnStatusChanged
import com.ugurbuga.codecase.extensions.doOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getProductDetailUseCase: GetProductDetailUseCase, savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    companion object {
        const val PRODUCT_ID = "product_id"
    }

    val productId: String? = savedStateHandle[PRODUCT_ID]

    private val _detail = MutableLiveData<ProductDetailUIModel>()
    val detail: LiveData<ProductDetailUIModel> get() = _detail

    init {
        getProductDetail()
    }

    private fun getProductDetail() {
        if (productId.isNullOrEmpty()) {
            _baseEvent.emitSuspending(BaseViewEvent.ShowErrorMessage(R.string.wrong_product_id))
        } else {
            getProductDetailUseCase(GetProductDetailUseCase.ProductDetailParams(productId)).doOnStatusChanged {
                initStatusState(
                    it
                )
            }.doOnSuccess {
                _detail.value = it
            }.launchIn(viewModelScope)
        }
    }

}