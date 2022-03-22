package com.ugurbuga.turkcellcodecase.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.viewbinding.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    private val _baseEvent = MutableSharedFlow<BaseViewEvent>()
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
                    //Show Error
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

    private fun showErrorMessage(message: Any, errorId: Int? = null) {
        _baseEvent.emitSuspending(BaseViewEvent.ShowErrorMessage(message, errorId))

    }

    private fun <T> MutableSharedFlow<T>.emitSuspending(value: T) =
        viewModelScope.launch(Dispatchers.IO) { emit(value) }
}
