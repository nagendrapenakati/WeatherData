package com.example.weatherapp.domain.repository

import com.example.weatherapp.data.model.Weather
import com.example.weatherapp.data.model.WeatherModel
import com.example.weatherapp.data.util.NetWorkResponse


interface WeatherRepository {

    suspend fun getWeather(city : String) : NetWorkResponse<WeatherModel>

}