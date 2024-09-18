package com.example.common.result

import com.example.common.network.ApiResponse
import com.example.common.network.NetworkError


sealed class Result <out T> {
    data class Success<T>(val value: T): Result<T>()
    data class Error(val message: String?): Result<Nothing>()

    companion object{
        fun <T> success(value: T): Success<T> = Success(value)
        fun failure(message: String?): Error = Error(message)
        fun getResultFromError(apiResponse: ApiResponse.Error): Result.Error =
            failure(
                when (apiResponse.error) {
                    is NetworkError.HttpError -> apiResponse.error.message
                    NetworkError.NetworkUnavailable -> apiResponse.error.message
                    NetworkError.Timeout -> apiResponse.error.message
                    is NetworkError.Unknown -> apiResponse.error.message
                }
            )
    }
}