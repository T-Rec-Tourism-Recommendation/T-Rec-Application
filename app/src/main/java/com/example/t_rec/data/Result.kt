package com.example.t_rec.data

sealed class Result<out R> private constructor() {
    data class Success<out T>(val data: T): Result<T>()
    data class Error(val error: Throwable): Result<Nothing>()
    data object Loading: Result<Nothing>()
}