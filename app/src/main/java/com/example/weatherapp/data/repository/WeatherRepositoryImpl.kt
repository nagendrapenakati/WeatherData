package com.example.weatherapp.data.repository

import com.example.weatherapp.data.api.WeatherService
import com.example.weatherapp.data.mapper.WeatherMapper
import com.example.weatherapp.data.model.Weather
import com.example.weatherapp.data.model.WeatherModel
import com.example.weatherapp.data.util.NetWorkResponse
import com.example.weatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherService: WeatherService,
    private val weatherMapper: WeatherMapper
) : WeatherRepository {
    override suspend fun getWeather(city: String): NetWorkResponse<WeatherModel> {

        return withContext(Dispatchers.IO){

            val res=weatherService.getWeather(city,"3cfdca9a402c8dbc443d7062c97a1c69","metric")

            if(res.isSuccessful){
                res.body()?.let {
                    item ->
                    var weatherData = weatherMapper.mapToWeather(item)
                    NetWorkResponse.success(weatherData)
                }?:NetWorkResponse.Error("Error Fetching Data")

            }
            else{
                NetWorkResponse.Error("Error Fetching Data")
            }

        }

    }


}