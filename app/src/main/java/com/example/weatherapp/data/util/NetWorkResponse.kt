package com.example.weatherapp.data.util

sealed class NetWorkResponse<out T> {
    data class success<out T>(val data:T): NetWorkResponse<T>()
    data class Error(val mes:String):NetWorkResponse<Nothing>()
    object Loading : NetWorkResponse<Nothing>()

}