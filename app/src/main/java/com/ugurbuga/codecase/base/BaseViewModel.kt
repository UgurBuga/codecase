package com.ugurbuga.codecase.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.viewbinding.BuildConfig
import com.ugurbuga.codecase.common.Status
import com.ugurbuga.codecase.data.error.GeneralErrorsHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected val _baseEvent = MutableSharedFlow<BaseViewEvent>()
    val baseEvent: SharedFlow<BaseViewEvent> = _baseEvent

    fun initStatusState(
        status: Status,
        isShowLoading: Boolean = true,
        isShowErrorMessage: Boolean = true,
        errorId: Int? = null,
    ) {
        when (status) {
            is Status.Loading -> {
                if (BuildConfig.DEBUG) {
                    Log.d("STATE ->", "LOADING")
                }
                if (isShowLoading) {
                    showLoading()
                }
            }
            is Status.Error -> {
                if (BuildConfig.DEBUG) {
                    Log.d("STATE ->", "ERROR ${status.exception?.message}")
                }
                if (isShowLoading) {
                    dismissLoading()
                }
                if (isShowErrorMessage) {
                    GeneralErrorsHandler(
                        { message, _ ->
                            showErrorMessage(message)
                        }, status.exception!!
                    )
                }
            }
            is Status.Success -> {
                if (BuildConfig.DEBUG) {
                    Log.d("STATE ->", "SUCCESS")
                }
                if (isShowLoading) {
                    dismissLoading()
                }
            }
        }
    }
    private fun showLoading() {
        _baseEvent.emitSuspending(BaseViewEvent.ShowLoading)
    }

    private fun dismissLoading() {
        _baseEvent.emitSuspending(BaseViewEvent.DismissLoading)

    }

    private fun showErrorMessage(message: Any) {
        _baseEvent.emitSuspending(BaseViewEvent.ShowErrorMessage(message))

    }

    fun <T> MutableSharedFlow<T>.emitSuspending(value: T) =
        viewModelScope.launch(Dispatchers.IO) { emit(value) }
}
