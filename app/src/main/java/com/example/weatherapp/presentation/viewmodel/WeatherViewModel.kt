package com.example.weatherapp.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.model.Weather
import com.example.weatherapp.data.model.WeatherModel
import com.example.weatherapp.data.util.NetWorkResponse
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.domain.usecase.WeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherUseCase: WeatherUseCase
)  : ViewModel() {

    val _liveData=MutableLiveData<NetWorkResponse<WeatherModel>>()

    val liveData : MutableLiveData<NetWorkResponse<WeatherModel>> = _liveData


    fun getWeather(city : String)
    {
        viewModelScope.launch {

            val res = weatherUseCase.invoke(city)
            _liveData.value=res

        }
    }

}