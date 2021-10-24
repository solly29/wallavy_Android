package com.example.wallavy.domain.model

sealed class Result <T> {
    data class Loading<T>(val start: Boolean): Result<T>()
    data class Success<T>(val data: T): Result<T>()
    data class Error<T>(val code: String, val msg: String): Result<T>()
}
