package com.example.mvvmtest.util

sealed class ResultResponse<out T : Any> {
    class Success<out T : Any>(val data: T) : ResultResponse<T>()
    class Error(val exception: Throwable) : ResultResponse<Nothing>()
}