package com.ugurbuga.codecase.data.error

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ErrorResponse(
    @SerializedName("Code") val code: String? = null,
    @SerializedName("Message") val message: String? = null,
    @SerializedName("RequestId") val requestId: String? = null,
    @SerializedName("HostId") val hostId: String? = null
) : Parcelable
