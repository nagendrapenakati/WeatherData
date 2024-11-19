package com.example.weatherapp.domain.usecase

import com.example.weatherapp.data.model.Weather
import com.example.weatherapp.data.model.WeatherModel
import com.example.weatherapp.data.util.NetWorkResponse
import com.example.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {

    suspend operator fun invoke(city: String): NetWorkResponse<WeatherModel>{
        return repository.getWeather(city)
    }

}