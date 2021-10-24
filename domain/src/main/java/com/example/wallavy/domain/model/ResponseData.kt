package com.example.wallavy.domain.model

data class ResponseData<T>(
    val size: String,
    val data: List<T>,
    val code: String,
    val msg: String
)