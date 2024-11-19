package com.example.weatherapp.data.api

import com.example.weatherapp.data.model.WeatherModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherService {

    @GET("data/2.5/weather?")
    suspend fun getWeather(
        @Query("q") q: String,
        @Query("appid") appid : String,
        @Query("units") units : String
    ) : Response<WeatherModel>

}