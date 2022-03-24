package com.ugurbuga.turkcellcodecase.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ugurbuga.turkcellcodecase.base.BaseViewModel
import com.ugurbuga.turkcellcodecase.domain.GetProductListUseCase
import com.ugurbuga.turkcellcodecase.domain.ProductUIModel
import com.ugurbuga.turkcellcodecase.domain.SetProductListUseCase
import com.ugurbuga.turkcellcodecase.extensions.doOnSuccess
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