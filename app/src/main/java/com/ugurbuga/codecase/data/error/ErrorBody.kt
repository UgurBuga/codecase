package com.ugurbuga.codecase.data.error

import android.os.Parcelable
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import fr.arnaudguyon.xmltojsonlib.XmlToJson
import java.nio.charset.Charset
import kotlinx.parcelize.Parcelize
import okhttp3.ResponseBody
import retrofit2.Response

@Parcelize
data class ErrorBody(
    var code: Int,
    @SerializedName("Error") val error: ErrorResponse? = null
) : Parcelable {

    companion object {
        const val UNKNOWN_ERROR = 0
        fun parseError(response: Response<*>?): ErrorBody? {
            return response?.let { res ->
                res.errorBody()?.let {
                    try {
                        val error = cloneBuffer(it)
                        val xmlToJson = XmlToJson.Builder(error).build()

                        var body = Gson().fromJson(xmlToJson.toString(), ErrorBody::class.java)

                        if (body == null) {
                            body = ErrorBody(res.code())
                        } else {
                            body.code = res.code()
                        }
                        body
                    } catch (ignored: Exception) {
                        ErrorBody(res.code())
                    }
                }
            }
        }

        private fun cloneBuffer(body: ResponseBody): String {
            val source = body.source()
            source.request(Long.MAX_VALUE)
            val buffer = source.buffer
            return buffer.clone().readString(Charset.forName("UTF-8"))
        }
    }
}