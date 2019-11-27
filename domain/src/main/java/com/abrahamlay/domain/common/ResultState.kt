package com.abrahamlay.domain.common

import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException

/**
 * Created by Abraham Lay on 2019-11-18.
 */
sealed class ResultState<T> {

    /**
     * A state of [data] which shows that we know there is still an update to come.
     */
    data class Loading<T>(val data: T) : ResultState<T>()

    /**
     * A state that shows the [data] is the last known update.
     */
    data class Success<T>(val data: T) : ResultState<T>()

    /**
     * A state to show a [throwable] is thrown.
     */
    data class Error<T>(val throwable: Throwable, val data: T?) : ResultState<T>() {
        fun getMessage(): String {
            if (throwable is HttpException) {
                if (throwable.code() == HttpURLConnection.HTTP_FORBIDDEN) return "Your request has reach limit (10 request) in a minute, please try again later"
                if (throwable.code() == 422) return "Missing your query, please input your search query."
            }

            if (throwable is SocketTimeoutException) return "Request Timeout, Please try again."

            if (throwable is IOException) return "Please, check your internet connection."
            return throwable.localizedMessage
        }
    }
}