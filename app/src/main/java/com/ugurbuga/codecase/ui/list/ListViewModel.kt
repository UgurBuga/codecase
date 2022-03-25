package com.ugurbuga.codecase.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ugurbuga.codecase.base.BaseViewModel
import com.ugurbuga.codecase.domain.GetProductListUseCase
import com.ugurbuga.codecase.domain.ProductUIModel
import com.ugurbuga.codecase.domain.SetProductListUseCase
import com.ugurbuga.codecase.extensions.doOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getProductListUseCase: GetProductListUseCase,
    private val setProductListUseCase: SetProductListUseCase,
) : BaseViewModel() {

    private val _list = MutableLiveData<List<ProductUIModel>>()
    val list: LiveData<List<ProductUIModel>> get() = _list

    init {
        getProductList()
    }

    private fun getProductList() {
        setProductListUseCase(SetProductListUseCase.ProductListParams()).launchIn(viewModelScope)

        getProductListUseCase(GetProductListUseCase.ProductListParams())
            .doOnSuccess {
                _list.value = it
            }.launchIn(viewModelScope)
    }
}