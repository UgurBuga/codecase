package com.ugurbuga.codecase.ui.list

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ugurbuga.codecase.base.BaseViewModel
import com.ugurbuga.codecase.domain.usecease.GetProductListUseCase
import com.ugurbuga.codecase.domain.model.ProductUIModel
import com.ugurbuga.codecase.extensions.doOnError
import com.ugurbuga.codecase.extensions.doOnStatusChanged
import com.ugurbuga.codecase.extensions.doOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn

@HiltViewModel
class ListViewModel @Inject constructor(
    private val setProductListUseCase: GetProductListUseCase,
) : BaseViewModel() {

    private val _viewState = MutableLiveData<ListViewState>()
    val viewState: LiveData<ListViewState> get() = _viewState

    private val _list = MutableLiveData<List<ProductUIModel>>()
    val list: LiveData<List<ProductUIModel>> get() = _list

    init {
        getProductList()
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun getProductList() {
        setProductListUseCase(Unit)
            .doOnStatusChanged {
                initStatusState(it)
            }
            .doOnSuccess {
                setData(it.products)
            }
            .doOnError {
                setData(arrayListOf())
            }
            .launchIn(viewModelScope)
    }

    fun setData(products: List<ProductUIModel>) {
        _list.value = products
        _viewState.value = ListViewState(products.isEmpty())
    }
}