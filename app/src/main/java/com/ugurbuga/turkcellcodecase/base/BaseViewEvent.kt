package com.ugurbuga.turkcellcodecase.base

sealed class BaseViewEvent {

    object ShowLoading : BaseViewEvent()

    object DismissLoading : BaseViewEvent()

    data class ShowErrorMessage(val message: Any, val errorId: Int? = null) : BaseViewEvent()

}
